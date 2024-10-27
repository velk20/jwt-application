INSERT INTO user_roles(id, user_role, description)
values ('e58ed763-928c-4155-bee9-fdbaaadc','ADMIN', 'Admin Permissions'),

       ('e58ed763-928c-4155-bee9-fdbaaad','USER', 'Simple User Permissions');

INSERT into users (id, email, first_name, is_active, last_name, password, username,phone,age, created_at, last_modified, user_role_id)
VALUES ('128ed763-928c-4155-bee9-fdbaaadc','admin@admin.com','Admin',1,'Adminov',
        '$2a$10$uLYimymH.0qx1cFGMLdqau8FGkAv3zxiRYGp/skhrqHLCPhdgN37G'
        ,'admin','0888288320',18,'2023-08-25T15:14:40.733400800','2023-08-25T15:14:40.733400800', 'e58ed763-928c-4155-bee9-fdbaaadc'),

       ('348ed763-928c-4155-bee9-fdbaaadc','user@user.com','User',1,'Userov',
        '$2a$10$8qDc5UdoxnKw90aniMpoqOHzMZ5aep.vvPdGMLCMG6VRxNjb.p7rS'
        ,'user','0888288321',20,'2023-08-25T15:14:40.733400800','2023-08-25T15:14:40.733400800', 'e58ed763-928c-4155-bee9-fdbaaad');
