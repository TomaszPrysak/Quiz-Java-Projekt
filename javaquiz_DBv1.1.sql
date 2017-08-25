create database javaquiz; 
use	javaquiz;
#drop database javaquiz; 
# ---  Table Users  ---

create table users (
  id_user INT NOT NULL AUTO_INCREMENT  ,
  imie VARCHAR(25) NOT NULL , 
  nazwisko VARCHAR(25) NOT NULL ,
  PRIMARY KEY (id_user) 
  #FOREIGN KEY (id_res) REFERENCES result (id_res) ,
  # FOREIGN KEY (id_q) REFERENCES questions (id_q) 
  );
  
# ---  Table Questions  ---

create table questions (
  id_q INT NOT NULL AUTO_INCREMENT ,
  tresc VARCHAR(250) NOT NULL ,  
  a TEXT NOT NULL , 
  b TEXT NOT NULL ,
  c TEXT NOT NULL ,
  d TEXT NOT NULL ,
  odp CHAR (1) , 
  kat VARCHAR(40) ,
  PRIMARY KEY (id_q) 
   );
  
 # ---  Table Result   ---

create table result (
  id_res INT   AUTO_INCREMENT,
  id_user INT ,
  res  DOUBLE(10,2) ,  
  PRIMARY KEY (id_res), 
  FOREIGN KEY (id_user) REFERENCES users (id_user)
  ); 
  
INSERT INTO users (id_user, imie, nazwisko ) VALUES (1, 'Tomasz', 'Aaa');
INSERT INTO users (id_user, imie, nazwisko ) VALUES (2, 'Kamil', 'Bbb');
INSERT INTO users (id_user, imie, nazwisko ) VALUES (3, 'Dariusz', 'Ccc');

INSERT INTO questions (id_q, tresc, a, b, c, d, odp, kat ) VALUES (1, 'Which one of the following will declare an array and initialize it with five numbers? ', ' int [] a = {23,22,21,20,19};', ' int [5] array;', ' int a [] = new int[5];', 'Array a = new Array(5);', 'a' , 'java' );
INSERT INTO questions (id_q, tresc, a, b, c, d, odp, kat ) VALUES (2, 'What is the numerical range of a char?', ' -128 to 127 ', ' 0 to 65535', ' -(215) to (215) - 1', ' 0 to 32767', 'b' , 'java' );
INSERT INTO questions (id_q, tresc, a, b, c, d, odp, kat ) VALUES (3, 'Which one of these lists contains only Java programming language keywords?', 'class, if, void, long, Int', ' class, if, void, long, Int', 'try, virtual, throw, final', 'goto, instanceof, native', 'd' , 'java' );
INSERT INTO questions (id_q, tresc, a, b, c, d, odp, kat ) VALUES (4, 'Which of the following function convert an integer to an unicode character in python? ', ' oct(x)', '  hex(x)', ' ord(x)', 'unichr(x)', 'd' , 'python' );
INSERT INTO questions (id_q, tresc, a, b, c, d, odp, kat ) VALUES (5, 'Which of the following data types is not supported in python?', ' Numbers', ' String', 'List', 'Slice', 'd' , 'python' );

select * from users ;
select * from questions ;
select * from result ;

select * from users where imie = 'Tomasz' and nazwisko = 'Aaa';

select * from questions where id_q = 1; 

select a from questions where id_q = 1;

select odp from questions where id_q = 1;

select count(id_q) from questions;

select count(kat) from questions where kat = 'java';

select count(kat) from questions where kat = 'python';

select count(kat) from questions where kat = 'python' or kat = 'java';

select * from questions where id_q = 1;

select sum(res) from result group by id_user having id_user = '1';

select count(id_user) from result group by id_user having id_user = '1';