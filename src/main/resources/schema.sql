--DROP TABLE IF EXISTS questions;
--
--CREATE TABLE questions (
--	id SERIAL PRIMARY KEY,
--	user_Id Integer ,
--	head varchar(140),
--	body varchar(1000),
--	poststamp Date NOT null
--);

DROP TABLE IF EXISTS responses;

CREATE TABLE responses (
	id SERIAL PRIMARY KEY,
	responder_Id Integer, -- FOREIGN KEY USER FROM  
	title varchar(140),
	body varchar(1000),
	creation_date Date NOT null
);
