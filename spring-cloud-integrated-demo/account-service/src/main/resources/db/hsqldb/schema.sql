DROP TABLE user IF EXISTS ;

CREATE TABLE user (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  password VARCHAR(20),
  age INTEGER
);
CREATE INDEX user_name ON user (name);

