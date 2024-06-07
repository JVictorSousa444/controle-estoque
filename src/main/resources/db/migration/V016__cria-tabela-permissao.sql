CREATE SEQUENCE permission_seq;

CREATE TABLE IF NOT EXISTS permission (
  id bigint NOT NULL DEFAULT NEXTVAL ('permission_seq'),
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)  ;
