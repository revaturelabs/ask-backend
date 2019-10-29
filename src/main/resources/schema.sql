DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS responses;
  
CREATE TABLE tags (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tag_name VARCHAR(30) UNIQUE
);


CREATE TABLE responses (
	id SERIAL PRIMARY KEY,
	responder_Id Integer, 
	title varchar(140),
	body varchar(1000),
	creation_date Date NOT null
);
