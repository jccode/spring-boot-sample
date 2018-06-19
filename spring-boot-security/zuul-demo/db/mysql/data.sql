-- BCryptPasswordEncoder
-- webapp / 000000
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('webapp', 'home,backend', '$2a$10$4GgPOTZx/Sdne6RwSuynlOfR34q9ykR/Yytt0IlYYX1ZcZCQAj.Hm', 'read,write,all', 'password,client_credentials,authorization_code,refresh_token,implicit', 'http://www.baidu.com/', 'user:create,user:read', null, null, null, null);
