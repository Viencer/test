
INSERT INTO lab3_personal (personal_id, first_name, last_name, boss_id, job_id, experience, salary) VALUES (LAB3_PERSONAL_SEQ.nextval, 'John', 'Smith', null, 1, 30, 10000);

INSERT INTO lab3_Roles (personal_id, user_name, password, role, enabled) VALUES (ROLESEQ.nextval, 'john', 'admin', 'ROLE_ADMIN', 1);

INSERT INTO lab3_personal (personal_id, first_name, last_name, boss_id, job_id, experience, salary) VALUES (LAB3_PERSONAL_SEQ.nextval, 'Doctor1', 'L_doctor1', 1, 2, 11, 3000);

INSERT INTO lab3_Roles (personal_id, user_name, password, role, enabled) VALUES (ROLESEQ.nextval, 'u1', 'u1', 'ROLE_USER', 1);

INSERT INTO lab3_personal (personal_id, first_name, last_name, boss_id, job_id, experience, salary) VALUES (LAB3_PERSONAL_SEQ.nextval, 'Doctor2', 'L_doctor2', 2, 2, 5, 1000);

INSERT INTO lab3_Roles (personal_id, user_name, password, role, enabled) VALUES (ROLESEQ.nextval, 'u2', 'u2', 'ROLE_USER', 1);

INSERT INTO lab3_personal (personal_id, first_name, last_name, boss_id, job_id, experience, salary) VALUES (LAB3_PERSONAL_SEQ.nextval, 'Doctor3', 'L_doctor3', 1, 1, 19, 7000);

INSERT INTO lab3_Roles (personal_id, user_name, password, role, enabled) VALUES (ROLESEQ.nextval, 'u3', 'u3', 'ROLE_USER', 1);
