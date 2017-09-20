use mvn_test_db;
create table if not exists t_user(
  id int(10) auto_increment PRIMARY  key,
  username VARCHAR(20) unique not null,
  password VARCHAR(20) not null,
  birthdate date
);