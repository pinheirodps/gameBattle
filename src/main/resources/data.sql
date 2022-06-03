insert into user (id, created_at, updated_at, password, username) values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
                                                                          '$2a$10$N4618E9whaai2zTFb7venOKyb1mV.bvLs0JkXP/tzNmSvwmNKUD0S', 'user01');
insert into user (id, created_at, updated_at, password, username) values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
                                                                          '$2a$10$N4618E9whaai2zTFb7venOKyb1mV.bvLs0JkXP/tzNmSvwmNKUD0S', 'user02');
insert into player (user_id, created_at, updated_at, score, username) values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),0, 'user01');
insert into player (user_id, created_at, updated_at, score, username) values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),0, 'user02');
