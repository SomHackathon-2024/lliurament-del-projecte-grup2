-- roles
INSERT INTO role(id,name) VALUES ('1','ROLE_USER');
INSERT INTO role(id,name) VALUES ('2','ROLE_ADMIN');

-- 3 categories created
-- INSERT INTO category (name) VALUES('web-dev');
-- INSERT INTO category (name) VALUES('data');
-- INSERT INTO category (name) VALUES('design');

-- 4 languages created
INSERT INTO language (name) VALUES('en');
INSERT INTO language (name) VALUES('cat');
INSERT INTO language (name) VALUES('es');
INSERT INTO language (name) VALUES('ru');

-- 10 users created
-- "$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2" == "password123"
INSERT INTO users (id, username, password, name, phone) VALUES ('1', 'admin', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'John', '+134123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('2', 'teacher', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Mary', '+234123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('3', 'user', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'David', '+334123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('4', 'sJohnson', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Susan', '+434123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('5', 'rBrown', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Robert', '+534123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('6', 'eDavis', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Emily', '+634123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('7', 'cWilson', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Christopher', '+734123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('8', 'lHarris', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Laura', '+834123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('9', 'aAnderson', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Alice', '+934123123123');
INSERT INTO users (id, username, password, name, phone) VALUES ('10', 'mThomas', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2', 'Matthew', '+034123123123');

-- assign roles to users
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (2, 2);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (3, 1);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (4, 1);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (4, 2);
