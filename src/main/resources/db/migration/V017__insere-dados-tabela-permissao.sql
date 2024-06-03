INSERT INTO permission (description) VALUES
    ('ADMIN'),
    ('MANAGER'),
    ('COMMON_USER');
    
ALTER SEQUENCE permission_seq RESTART WITH 4;
