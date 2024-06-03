
CREATE TABLE IF NOT EXISTS user_permission (
  id_user bigint NOT NULL,
  id_permission bigint NOT NULL,
  PRIMARY KEY (id_user,id_permission),
  CONSTRAINT fk_user_permission FOREIGN KEY (id_user) REFERENCES users (id),
  CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission (id)
) ;

INSERT INTO user_permission (id_user, id_permission) VALUES
    (1, 1),
    (2, 1),
    (1, 2),
    (3, 1),
    (3, 2),
    (3, 3);