-- root 계정으로 로그인해서 실행
-- 데이터베이스, 유저 생성
DROP DATABASE IF EXISTS duk;
CREATE DATABASE duk;

DROP USER IF EXISTS dukadmin@'%';
CREATE USER dukadmin@'%' IDENTIFIED BY '123456';

GRANT ALL PRIVILEGES ON duk.* TO dukadmin@'%';


-- 테이블 생성
USE duk;

DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
	`seq`	int	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
	`name`	varchar(255) UNIQUE,
	`phone`	varchar(255)	NOT NULL,
	`loc`	varchar(255)	NOT NULL,
	`loc_detail`	varchar(255)	NOT NULL,
	`intro`	varchar(255)	NOT NULL
);

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
	`seq`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`dept_seq`	int	NOT NULL,
	`dept_name`	varchar(255)	NOT NULL,
	`id`	varchar(255)	NOT NULL	UNIQUE,
	`pwd`	varchar(255)	NOT NULL,
	`name`	varchar(255)	NOT NULL,
	`email`	varchar(255)	NOT NULL	UNIQUE,
	`phone`	varchar(255)	NOT NULL,
	`status`	tinyint	NOT NULL	DEFAULT 0	COMMENT '거부,검토,승인/-1,0,1',
	`type`	tinyint	NOT NULL	DEFAULT 1	COMMENT '일반,일반관리자,관리자/0,1,2',
    FOREIGN KEY(dept_seq) REFERENCES dept(seq)
);

DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
	`seq`	int	NOT NULL PRIMARY KEY	AUTO_INCREMENT,
	`type`	tinyint	NOT NULL	DEFAULT 0,
    `type2`	tinyint	NOT NULL	DEFAULT 0,
	`title`	varchar(255)	NOT NULL,
	`content`	varchar(255)	NOT NULL,
	`hit`	int	NOT NULL	DEFAULT 0,
	`apply_start`	datetime	NULL	COMMENT 'LocalDateTime 형 엔티티 컬럼',
	`apply_end`	datetime	NULL	COMMENT 'LocalDateTime 형 엔티티 컬럼',
	`event_start`	datetime	NULL	COMMENT 'LocalDateTime 형 엔티티 컬럼',
	`event_end`	datetime	NULL	COMMENT 'LocalDateTime 형 엔티티 컬럼',
	`filename`	varchar(255)	NULL,
	`calendar_color`	varchar(255)	NOT NULL	COMMENT 'rgb 코드 저장',
	`writer_dept_seq`	int	NOT NULL	COMMENT 'fk, 작성자명(부서명) 출력용',
	`writer_member_seq`	int	NOT NULL	COMMENT 'fk, 작성자 식별용',
    FOREIGN KEY(writer_dept_seq) REFERENCES dept(seq),
    FOREIGN KEY(writer_member_seq) REFERENCES member(seq)
);

DROP TABLE IF EXISTS `bookmark`;
CREATE TABLE `bookmark` (
	`seq`	int	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
	`member_seq`	int	NOT NULL,
	`board_seq`	varchar(255)	NOT NULL	COMMENT '즐겨찾기한 게시글 번호 ","으로 구분',
    FOREIGN KEY(member_seq) REFERENCES member(seq)
);

-- 부서 loc 자료형 고민 필요 건물 구분을 숫자로 할지, 문자열로 그냥 지정할 지
INSERT INTO dept(seq, name, phone, loc, loc_detail, intro)
	VALUES(0, '관리자', '000-0000-0000', '관리부서', '관리부서', '관리부서');
 
INSERT INTO member(seq, dept_seq, dept_name, id, pwd, name, email, phone, status, type)
	values(0, 1, '관리자', 'dukadmin', '123456', '관리자', 'test@domain.com', '010-0000-0000', 1, 2);
