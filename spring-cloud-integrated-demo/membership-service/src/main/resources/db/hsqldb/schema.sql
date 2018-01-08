DROP TABLE member_point_flow IF EXISTS ;

CREATE TABLE member_point_flow (
  id   INTEGER IDENTITY PRIMARY KEY,
  user_id INTEGER NOT NULL ,
  order_id INTEGER NOT NULL ,
  point INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

-- ALTER TABLE member_point_flow ADD FOREIGN KEY (user_id) REFERENCES USER(id);
-- ALTER TABLE member_point_flow ADD FOREIGN KEY (order_id) REFERENCES "ORDER"(id);

CREATE INDEX member_point_flow_user_id ON member_point_flow (user_id);
CREATE INDEX member_point_flow_order_id ON member_point_flow (order_id);



DROP TABLE member_points IF EXISTS ;

CREATE TABLE member_points (
  id   INTEGER IDENTITY PRIMARY KEY,
  user_id INTEGER NOT NULL ,
  points INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

-- ALTER TABLE member_points ADD FOREIGN KEY (user_id) REFERENCES USER(id);

CREATE INDEX member_points_user_id ON member_points (user_id);

