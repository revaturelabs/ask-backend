DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
	id SERIAL PRIMARY KEY,
	user_Id Integer ,
	head varchar(100),
	body varchar(100),
	creation_date Date NOT null
);

DROP TABLE IF EXISTS tags;
  
CREATE TABLE tags (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tag_name VARCHAR(30) UNIQUE
);