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
	`intro`	varchar(255)	NOT NULL,
    `board_type` int
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
    `date`  datetime    NOT NULL,
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
INSERT INTO dept(name, phone, loc, loc_detail, intro, board_type)
	VALUES('관리자부서', '000-0000-0000', '관리부서', '관리부서', '관리부서',null);
INSERT INTO dept(name, phone, loc, loc_detail, intro, board_type)
values('유아교육과','02-3399-1576','제1실습관', '305호','유아들의 전인 발달에 기초한 교육과정을 통하여 유아를 진정으로 사랑하고 교육하는 유능한 교사를 양성하는 것을 목표로 삼고있다. 봉사의 가치를 알고, 유아의 더 나은 삶에 대한 책임감과 소명의식을 가진 마음이 따뜻한 사람, 아동의 발달과 사회 환경에 대한 지식과 기술을 가진 전문적인 사람, 빠르게 변화하는 시대에 대처할 수 있는 창의력과 문제해결능력이 뛰어난 사람을 길러내는 것을 본 학과의 인간상으로 삼고 교육하고 있다.',null);

INSERT INTO dept(name, phone, loc, loc_detail, intro, board_type)
values('바이오융합공학과','02-3399-1961','에스라관','310호','바이오 식의약 및 기능성 바이오 소재들을 발굴하여 고기능성 의약품, 건강기능식품 및 화장품을 미래지향적으로 연구하는 창의적 융합인재를 양성한다.',null);

INSERT INTO dept(name, phone, loc, loc_detail, intro, board_type)
values('물리치료학과','02-3399-1638','제3과학관','101호','졸업 후 진로는 국가면허시험에 합격하여 보건복지가족부장관의 면허를 취득한 후 의료기관(종합병원, 병원, 한방병원, 재활전문병원, 의원, 요양병원 등)과 스포츠관련기관(스포츠 구단 의무실, 운동처방실, 스포츠 연구소 등), 각 기업체 의무실, 재활관련 연구소, 재활관련 정부기관, 재활원, 장애인 복지관, 보건소 등에 취업을 할 수 있다. 또한 세계 각국(미국 등)의 물리치료사 면허취득으로 해외 취업도 가능하다.',null);

INSERT INTO dept(name, phone, loc, loc_detail, intro, board_type)
values('식품영양학과','02-3399-1654','제1과학관','103호','식생활 문화의 확대와 식품・외식 산업의 성장에 대하여 능동적으로 대처 가능한 이론・실무 및 소통 능력을 갖춘 FOOD SERVICE 산업의 창의적 경영 및 관리자를 양성한다.',null);

select * from dept;

INSERT INTO member(seq, dept_seq, dept_name, id, pwd, name, email, phone, status, type)
	values(0, 1, '관리자', 'dukadmin', '123456', '관리자', 'test@domain.com', '010-0000-0000', 1, 2);

select * from member;