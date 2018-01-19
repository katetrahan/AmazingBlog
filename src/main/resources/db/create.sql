SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS posts (
 id int PRIMARY KEY auto_increment,
 content VARCHAR,
 authorId INTEGER

 );

CREATE TABLE IF NOT EXISTS authors (
 id int PRIMARY KEY auto_increment,
 name VARCHAR
 );