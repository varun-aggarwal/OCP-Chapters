create database employee_demo;

CREATE TABLE employee
(
  id numeric NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
  name character varying(35),
  CONSTRAINT employee_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE employee
  OWNER TO postgres;


CREATE TABLE address
(
  street character varying,
  empid numeric NOT NULL,
  CONSTRAINT address_fk FOREIGN KEY (empid)
      REFERENCES employee (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE address
  OWNER TO postgres;


CREATE SEQUENCE employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE employee_id_seq OWNER TO postgres;

ALTER SEQUENCE employee_id_seq OWNED BY employee.id;

ALTER TABLE ONLY employee ALTER COLUMN id SET DEFAULT nextval('employee_id_seq'::regclass);

insert into employee (name) values ('Varun');

insert into address (street,empid) values ('Amstelveenseweg',1);

commit;