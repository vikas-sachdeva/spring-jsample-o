DROP TABLE IF EXISTS application;

CREATE TABLE application (id SERIAL PRIMARY KEY, name VARCHAR, running BOOLEAN);

INSERT INTO application ( name, running) VALUES ('app-1',false);
INSERT INTO application ( name, running) VALUES ('app-2',false);
INSERT INTO application ( name, running) VALUES ('app-3',true);