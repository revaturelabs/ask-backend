-- Mock data added 103019 by @author Efrain VIla

--data for the users table
INSERT INTO users VALUES
    (default,'novice','1234',false), -- user login
    (default,'newbie','1234',false), -- user login 2
    (default,'expert','4321',true), -- expert login
    (default,'smartguy','4321',true);  -- expert login 2

-- data for tags table
INSERT INTO tags VALUES
    (DEFAULT, 'JavaScript'),
    (DEFAULT, 'SQL'),
    (DEFAULT, 'Java'),
    (DEFAULT, 'HTML'),
    (DEFAULT, 'Angular'),
    (DEFAULT, 'Bootstrap');

-- data for questions
INSERT INTO questions VALUES 
  (DEFAULT, 1, null, 'Question head 1', 'Question body 1', '2015-12-17'), -- 3rd parameter is highlighted_response_id (null)
  (DEFAULT, 2, null, 'Question head 2', 'Question body 2', '2015-12-17'),
  (DEFAULT, 1, null, 'Question head 3', 'Question body 3', '2015-12-17'),
  (DEFAULT, 2, null, 'Question head 4', 'Question body 4', '2015-12-17');

-- data for responses
INSERT INTO responses VALUES 
  (DEFAULT, 1, 3,'Response body 1', '2019-10-31'),
  (DEFAULT, 2, 4,'Response body 2', '2019-10-31'),
  (DEFAULT, 2, 3,'Response body 3', '2019-10-31'),
  (DEFAULT, 1, 4,'Response body 4', '2019-10-31');

-- data for questions_tags
-- this is a junction table for questions and tags
INSERT INTO questions_tags VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (1, 3),
    (2, 1);
 --   (DEFAULT, DEFAULT); -- if needed
 
-- data for user_tags
-- this a junction table for users and tags
 INSERT INTO users_tags VALUES
    (3, 1),
    (4, 2),
    (3, 3);
 --   (DEFAULT, DEFAULT); -- if needed