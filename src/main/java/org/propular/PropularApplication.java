package org.propular;

import java.security.KeyPair;

import org.propular.dto.security.AppClient;
import org.propular.dto.security.AppUser;
import org.propular.service.dao.AppClientsRepository;
import org.propular.service.dao.AppUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PropularApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(PropularApplication.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class UserDetailsSecurityConfig extends
			WebSecurityConfigurerAdapter {

		@Autowired
		AppUserDetailsService userDetailsService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/css/**").permitAll().anyRequest()
			.fullyAuthenticated().and().formLogin().loginPage("/login")
			.failureUrl("/login?error").permitAll().and().logout().permitAll();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(
					passwordEncoder());
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends
			AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private AppClientsUserDetailsService appClientsUserDetailsService;

		@Autowired
		AppUserDetailsService userDetailsService;

		@Value("${propular.keystore.password}")
		private String jksPassword;

		@Value("${propular.keystore.path}")
		private String jksPath;

		@Value("${propular.keystore.keypair}")
		private String jksKeypair;

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter()
				throws Exception {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			KeyPair keyPair = new KeyStoreKeyFactory(
					PropularApplication.getResource(jksPath),
					jksPassword.toCharArray()).getKeyPair(jksKeypair);
			converter.setKeyPair(keyPair);
			return converter;
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			clients.withClientDetails(appClientsUserDetailsService);
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.authenticationManager(authenticationManager)
					.userDetailsService(userDetailsService)
					.accessTokenConverter(jwtAccessTokenConverter());
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer)
				throws Exception {
			oauthServer.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()")
					.passwordEncoder(new BCryptPasswordEncoder());
		}

	}

	@Service
	protected static class AppClientsUserDetailsService implements
			ClientDetailsService {

		@Autowired
		AppClientsRepository appClientsRepository;

		@Override
		public ClientDetails loadClientByClientId(String clientId)
				throws ClientRegistrationException {
			AppClient client = appClientsRepository.findByClientId(clientId);
			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId(client.clientId);
			clientDetails.setClientSecret(client.clientSecret);
			clientDetails.setScope(client.getScopes());
			clientDetails.setAuthorizedGrantTypes(client.getGrantTypes());
			return clientDetails;
		}

	}

	@Service
	public class AppUserDetailsService implements UserDetailsService {

		@Autowired
		private AppUsersRepository userRepository;

		@Override
		public UserDetails loadUserByUsername(String username)
				throws UsernameNotFoundException {
			AppUser user = userRepository.findByUserName(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			} else {
				UserDetails details = new org.springframework.security.core.userdetails.User(
						user.userName, user.password, true, true, true, true,
						user.getRoles());
				return details;
			}
		}
	}

	private static Resource getResource(String path) throws Exception {
		try {
			if (new ClassPathResource(path).exists()) {
				return new ClassPathResource(path);
			} else if (new UrlResource(path).exists()) {
				return new UrlResource(path);
			} else if (new FileSystemResource(path).exists()) {
				return new FileSystemResource(path);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
