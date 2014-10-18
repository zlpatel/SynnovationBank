CREATE TABLE users (
    username VARCHAR(45) NOT NULL,
    password VARCHAR(60) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);
 
CREATE TABLE user_roles (
    user_role_id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL,
    ROLE VARCHAR(45) NOT NULL,
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uni_username_role (ROLE , username),
    KEY fk_username_idx (username),
    CONSTRAINT fk_username FOREIGN KEY (username)
        REFERENCES users (username)
);

INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$Q.sjD9Y5BSh.JFh2RLSO5uzqfP7KkxmZJZf.i4t2OopcoF8vo7H2e', TRUE);
INSERT INTO users(username,password,enabled)
VALUES ('employee','$2a$10$YelKD8B6zNSyi0VrIZ4zkeyxz.R.zpb/lsAiGEzcCshuW3qUi5rWW', TRUE);
INSERT INTO users(username,password,enabled)
VALUES ('customer','$2a$10$TJoIZYAY9VZxC1jyk8b60eZC731wOX0kThB9OfkpZIsGFd2uJ2njC', TRUE);
INSERT INTO users(username,password,enabled)
VALUES ('merchant','$2a$10$xI2b2CarqumZEIg2400TBOeMkT8ZbeON/7LhCZVloQx5FcHPqGYlKuser_rolesuser_roles', TRUE);
 
INSERT INTO user_roles (username, ROLE)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, ROLE)
VALUES ('employee', 'ROLE_BNK_EMPL');
INSERT INTO user_roles (username, ROLE)
VALUES ('customer', 'ROLE_CUST');
INSERT INTO user_roles (username, ROLE)
VALUES ('merchant', 'ROLE_MERC');