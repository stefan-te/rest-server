# insert sample users
INSERT IGNORE INTO user (login, password, fname, lname, email) VALUES
('m.winter', '7288edd0fc3ffcbe93a0cf06e3568e28521687bc', 'Monika', 'Winter', 'm.winter@gmail.com'),
('a.sommer', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'Alexander', 'Sommer', 'a.sommer@gmail.com');

# insert sample roles
INSERT IGNORE INTO role (user_id, role_admin, role_develop, role_cctld, role_gtld, role_billing, role_registry, role_purchase_read, role_purchase_write, role_sale_write, role_sql) VALUES
((SELECT id from user WHERE login='m.winter'), 1, 1, 1, 1, 0, 1, 0, 0, 0, 1),
((SELECT id from user WHERE login='a.sommer'), 0, 0, 0, 0, 1, 0, 1, 1, 1, 0);