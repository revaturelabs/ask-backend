DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
	id SERIAL PRIMARY KEY,
	userId Integer NOT NULL,
	head varchar(100),
	body varchar(100),
	poststamp Date NOT null
);