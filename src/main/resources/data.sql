# insert sample users
INSERT IGNORE INTO user (login, password, fname, lname, email) VALUES
('m.winter', '$2y$10$FvnzalZlKqTrwdESLLhVtuXQV2yGAD.8OfyXTNQdtyKkb/YUhR4my', 'Monika', 'Winter', 'm.winter@gmail.com'),
('a.sommer', '$2y$10$yp0zKnEvrLC1T1SgBn.JGOdFP4d1gFrXRoaxYStbmXPDgdfn/TVfO', 'Alexander', 'Sommer', 'a.sommer@gmail.com');

# insert sample roles
INSERT IGNORE INTO role (user_id, role_admin, role_develop, role_cctld, role_gtld, role_billing, role_registry, role_purchase_read, role_purchase_write, role_sale_write, role_sql) VALUES
((SELECT id from user WHERE login='m.winter'), 1, 1, 1, 1, 0, 1, 0, 0, 0, 1),
((SELECT id from user WHERE login='a.sommer'), 0, 0, 0, 0, 1, 0, 1, 1, 1, 0);