create database springsession default character set=utf8mb4;
create user 'springsession'@'localhost' identified by 'springsession';
grant all privileges on springsession.* to 'springsession'@'localhost';

alter user 'springsession'@'localhost' identified with mysql_native_password  by 'springsession';
flush privileges;
