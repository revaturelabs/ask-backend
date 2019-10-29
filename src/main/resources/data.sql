
--data for the users table
INSERT INTO users VALUES(default,'joke','aahahahahaha',false);
INSERT INTO users VALUES(default,'mweklf','wfjwioej',true);

INSERT INTO questions VALUES 
  (DEFAULT, 9, 'what ever header', 'what ever post', '2015-12-17'),
  (DEFAULT, 8, 'what ever ', 'what ever ', '2015-12-17'),
  (DEFAULT, 7, 'what ever boy', 'what ever bsfga', '2015-12-17'),
  (DEFAULT, 6, 'what ever asfgaa', 'what ever grgsgg', '2015-12-17');
  
INSERT INTO tags VALUES
	(1, 'JavaScript'),
	(2, 'SQL'),
	(3, 'Java'),
	(4, 'HTML'),
	(5, 'Angular'),
	(6, 'Bootstrap');

INSERT INTO responses VALUES 
  (1, 9, 'what ever title 1', 'what ever body 11', '2019-10-31'),
  (2, 8, 'what ever title 2', 'what ever body 22', '2019-10-31'),
  (3, 7, 'what ever title 3', 'what ever body 33', '2019-10-31'),
  (4, 6, 'what ever title 4', 'what ever body 44', '2019-10-31');

INSERT INTO questions_tags VALUES
 (1, 1),
 (1, 2),
 (2, 3);

