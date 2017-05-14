#db.app_clients.insert({ "_id" : "acme", "client_secret" : "$2a$10$Q2509xjkOVEkw96LLuAtNeTzCZNtIRr4JR8Tc0IykmPYS//046uHa", "scopes" : "read,write", "grant_types" : "client_credentials,password,refresh_token,authorization_code" });

db.app_users.insert({ "_id" : "user1", "password" : "$2a$10$wzyQPSxj5OyNMqWCKD02tOxsWhpSzBlLqIVxK1ugBrx5iGtKJufsG", "roles" : "ADMIN,USER" },{ "_id" : "acme", "password" : "$2a$10$Q2509xjkOVEkw96LLuAtNeTzCZNtIRr4JR8Tc0IykmPYS//046uHa", "scopes" : "read,write", "grant_types" : "client_credentials,password,refresh_token,authorization_code" });

db.app_users_role.insert([{"name" : "ADMIN" }, {"name" : "USER" },{ "name" : "SUPPORT" },{"name" : "MONITOR" }]);

db.app_clients_role.insert([{"name" : "read" },{ "name" : "write" });

db.app_clients_grants.insert([{grant: "client_credentials"}, {grant : "password"}, {grant: "refresh_token"}, {grant : "authorization_code"}]);