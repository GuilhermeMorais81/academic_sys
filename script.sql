CREATE TABLE T_COURSE 
(
    id_course UUID NOT NULL, 
    nm_course VARCHAR(160) NOT NULL 
);

ALTER TABLE T_COURSE ADD CONSTRAINT T_COURSE_PK PRIMARY KEY (id_course);
ALTER TABLE T_COURSE ADD CONSTRAINT COURSE_NAME__UN UNIQUE (nm_course);

CREATE TABLE T_COURSE_ENROLL 
(
    id_course UUID NOT NULL, 
    id_student UUID NOT NULL, 
    id_course_enroll UUID NOT NULL, 
    dt_creation DATE NOT NULL, 
    status SMALLINT NOT NULL 
);

-- 0: in course
-- 1: approved
-- 2: not approved
-- 3: withdawn

ALTER TABLE T_COURSE_ENROLL ADD CONSTRAINT T_COURSE_ENROLL_PK PRIMARY KEY (id_course_enroll);

CREATE TABLE T_DIS_ENROLL 
(
    id_course_enroll UUID NOT NULL, 
    id_dis_enroll UUID NOT NULL, 
    first_avg DECIMAL(4,2), 
    second_avg DECIMAL(4,2), 
    final_avg DECIMAL(4,2), 
    status SMALLINT NOT NULL, 
    id_discipline UUID NOT NULL 
);

ALTER TABLE T_DIS_ENROLL ADD CONSTRAINT T_DIS_ENROLL_PK PRIMARY KEY (id_dis_enroll);

CREATE TABLE T_DISCIPLINE 
(
    id_course UUID NOT NULL, 
    id_discipline UUID NOT NULL, 
    nm_discipline VARCHAR(160) NOT NULL 
);

ALTER TABLE T_DISCIPLINE ADD CONSTRAINT T_DISCIPLINE_PK PRIMARY KEY (id_discipline);

CREATE TABLE T_STUDENT 
(
    id_student UUID NOT NULL, 
    nm_student VARCHAR(160) NOT NULL, 
    dt_birth DATE NOT NULL, 
    SSN CHAR(9) NOT NULL 
);

ALTER TABLE T_STUDENT ADD CONSTRAINT T_STUDENT_PK PRIMARY KEY (id_student);
ALTER TABLE T_STUDENT ADD CONSTRAINT STUDENT_SSN__UN UNIQUE (SSN);

ALTER TABLE T_COURSE_ENROLL 
    ADD CONSTRAINT T_COURSE_ENROLL_T_COURSE_FK FOREIGN KEY (id_course) 
    REFERENCES T_COURSE (id_course) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE T_COURSE_ENROLL 
    ADD CONSTRAINT T_COURSE_ENROLL_T_STUDENT_FK FOREIGN KEY (id_student) 
    REFERENCES T_STUDENT (id_student) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE T_DIS_ENROLL 
    ADD CONSTRAINT T_DIS_ENROLL_T_COURSE_FK FOREIGN KEY (id_course_enroll) 
    REFERENCES T_COURSE_ENROLL (id_course_enroll) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE T_DIS_ENROLL 
    ADD CONSTRAINT T_DIS_ENROLL_T_DISCIPLINE_FK FOREIGN KEY (id_discipline) 
    REFERENCES T_DISCIPLINE (id_discipline) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE T_DISCIPLINE 
    ADD CONSTRAINT T_DISCIPLINE_T_COURSE_FK FOREIGN KEY (id_course) 
    REFERENCES T_COURSE (id_course) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE t_course_enroll
ADD CONSTRAINT UQ_STUDENT_COURSE UNIQUE(id_course, id_student);


CREATE OR REPLACE VIEW VW_DISCIPLINE_ENROLL AS
SELECT
    t_student.nm_student,
    t_student.dt_birth,
    t_course.nm_course,
    t_discipline.nm_discipline,
    t_course_enroll.dt_creation,
    t_dis_enroll.final_avg
FROM 
    t_course_enroll
    INNER JOIN t_student ON t_course_enroll.id_student = t_student.id_student
    INNER JOIN t_course ON t_course_enroll.id_course = t_course.id_course
    INNER JOIN t_dis_enroll ON t_course_enroll.id_course_enroll = t_dis_enroll.id_course_enroll
    INNER JOIN t_discipline ON t_dis_enroll.id_discipline = t_discipline.id_discipline;

CREATE OR REPLACE VIEW VW_DISCIPLINE_ENROLL AS
SELECT t_student.nm_student,
    t_student.ssn,
    t_course.nm_course,
    t_course_enroll.status
   FROM ((t_course_enroll
     JOIN t_student ON ((t_course_enroll.id_student = t_student.id_student)))
     JOIN t_course ON ((t_course_enroll.id_course = t_course.id_course)));