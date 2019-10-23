DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
	id SERIAL PRIMARY KEY,
	user_Id Integer ,
	head varchar(100),
	body varchar(100),
	creation_date Date NOT null
);