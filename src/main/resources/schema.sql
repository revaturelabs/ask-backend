DROP TABLE IF EXISTS questions_tags;
--users table
DROP TABLE IF EXISTS USERS;

DROP TABLE IF EXISTS tags;

DROP TABLE IF EXISTS questions;

DROP TABLE IF EXISTS responses;
  
DROP TABLE IF EXISTS questions_tags;

CREATE TABLE USERS (
	id SERIAL PRIMARY KEY,
	username VARCHAR,
	password VARCHAR,
	expert BOOLEAN
);

CREATE TABLE tags (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tag_name VARCHAR(30) UNIQUE
);


CREATE TABLE questions (
  id SERIAL PRIMARY KEY,
  user_Id Integer ,
  head varchar(140),
  body varchar(1000),
  creation_Date Date NOT null
);

CREATE TABLE responses (
  id SERIAL PRIMARY KEY,
  responder_Id Integer, 
  body varchar(1000),
  creation_date Date NOT null
);

CREATE TABLE questions_tags (
 question_id INT REFERENCES questions(id),
 tags_id INT REFERENCES tags(id),
 CONSTRAINT id PRIMARY KEY (question_id, tags_id)
);