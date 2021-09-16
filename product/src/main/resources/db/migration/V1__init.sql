create table products (
                          id                      bigserial primary key,
                          title                   varchar(255),
                          price                   double,
                          created_at timestamp default current_timestamp,
                          updated_at timestamp default current_timestamp
);

insert into products (title, price)
values
('milk', 10),
('water', 11),
('meat', 15),
('ice', 1),
('tv', 4),
('snack', 2),
('hotdog', 56),
('chease creme', 4),
('sea food', 55),
('pizza', 7),
('apple', 4),
('garlick', 10),
('rice', 5)
;