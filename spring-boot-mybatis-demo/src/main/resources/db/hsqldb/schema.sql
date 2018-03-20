DROP TABLE user IF EXISTS ;

CREATE TABLE user (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  password VARCHAR(20),
  age INTEGER
);
CREATE INDEX user_name ON user (name);

CREATE TABLE orders (
  id   INTEGER IDENTITY PRIMARY KEY,
  user_id INTEGER,
  no VARCHAR(20),
  note VARCHAR(100)
);
CREATE INDEX order_no on orders (no);

CREATE TABLE order_detail (
  id   INTEGER IDENTITY PRIMARY KEY,
  orders_id INTEGER,
  product_id INTEGER,
  amount INTEGER
);
CREATE INDEX order_detail_oid on order_detail(orders_id);

CREATE TABLE product (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  price DOUBLE
);
CREATE INDEX product_name on product(name);


