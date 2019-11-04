DROP TABLE IF EXISTS questions_tags;
DROP TABLE IF EXISTS users_tags;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS responses;
DROP TABLE IF EXISTS image;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR,
  password VARCHAR,
  expert BOOLEAN
);

CREATE TABLE tags (
  id SERIAL PRIMARY KEY,
  name VARCHAR UNIQUE
);

CREATE TABLE questions (
  id SERIAL PRIMARY KEY,
  questioner_id INTEGER,
  highlighted_response_id INTEGER NULL,
  head VARCHAR,
  body VARCHAR,
  creation_date DATE NOT NULL,
  FOREIGN KEY (questioner_id) REFERENCES users (id)
);

CREATE TABLE responses (
  id SERIAL PRIMARY KEY,
  responder_id INTEGER,
  question_id INTEGER NOT NULL,
  body VARCHAR,
  creation_date DATE NOT NULL,
  FOREIGN KEY (responder_id) REFERENCES users (id),
  FOREIGN KEY (question_id) REFERENCES questions (id)
);

ALTER TABLE questions
ADD CONSTRAINT highlight_response_fk
FOREIGN KEY (highlighted_response_id) REFERENCES responses (id);

CREATE TABLE questions_tags (
  question_id INTEGER,
  tag_id INTEGER,
  CONSTRAINT question_no_null_or_duplicate_tags PRIMARY KEY (question_id, tag_id),
  FOREIGN KEY (question_id) REFERENCES questions (id),
  FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE users_tags (
  user_id INTEGER,
  tag_id INTEGER,
  CONSTRAINT user_no_null_or_duplicate_tags PRIMARY KEY (user_id, tag_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE image (
  id SERIAL PRIMARY KEY,
  image bytea
);