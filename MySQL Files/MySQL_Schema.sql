#-- This is SQL Project done by Jatan Patel under the guidance of Dr. Chris Davis. --#
CREATE DATABASE Library;
# Developing Library schema

USE Library;

CREATE TABLE BOOK
(
	book_id char(10) not null,
	title varchar(100) not null,
	CONSTRAINT isbn primary key (book_id)
);

CREATE TABLE BOOK_AUTHORS
(
	book_id char(10) not null,
	author_name varchar(50) not null,
	CONSTRAINT frontpage primary key (book_id,author_name)
);

CREATE TABLE LIBRARY_BRANCH
(
	branch_id varchar(10) not null,
	branch_name varchar(25) not null,
	address varchar(50) not null,
	CONSTRAINT pk_branch primary key (branch_id)
);
#SHOW ENGINE INNODB STATUS;
CREATE TABLE BOOK_COPIES
(
	book_id char(10) not null,
	branch_id varchar(10) not null,
	n int not null,
	CONSTRAINT pk_book_copies primary key (book_id,branch_id)
);

CREATE TABLE BORROWER
(
	card_no int not null auto_increment,
	fname varchar(15) not null,
	lname varchar(15) not null,
	address varchar(50) not null,
	phone varchar(35),
	CONSTRAINT pk_card_no primary key (card_no)
);

CREATE TABLE BOOK_LOANS
(
	loan_id int not null auto_increment,
	book_id char(10) not null,
	branch_id varchar(10) not null,
	card_no int not null,
	date_out date not null,
	due_date date not null,
	date_in date,
	CONSTRAINT pk_book_loans primary key (loan_id)
);

CREATE TABLE FINES
(
	loan_id int not null,
	fine_amt double,
	paid int(1),
	CONSTRAINT pk_fines primary key (loan_id)
);


ALTER TABLE BOOK_AUTHORS ADD CONSTRAINT fk_book_authors foreign key (book_id) references BOOK(book_id);

ALTER TABLE BOOK_COPIES ADD CONSTRAINT fk1_book_copies foreign key (book_id) references BOOK(book_id);
ALTER TABLE BOOK_COPIES ADD	CONSTRAINT fk2_book_copies foreign key (branch_id) references LIBRARY_BRANCH(branch_id);

ALTER TABLE BOOK_LOANS ADD CONSTRAINT fk_book_loans_book foreign key (book_id) references BOOK(book_id);
ALTER TABLE BOOK_LOANS ADD CONSTRAINT fk_book_loans_branch foreign key (branch_id) references LIBRARY_BRANCH(branch_id);
ALTER TABLE BOOK_LOANS ADD CONSTRAINT fk_book_loans_borrower foreign key (card_no) references BORROWER(card_no);

ALTER TABLE BOOK_AUTHORS ADD author_type INT(1) DEFAULT 1;
ALTER TABLE BOOK_AUTHORS ADD role varchar(20) DEFAULT "Author";

ALTER TABLE FINES ADD CONSTRAINT fk_fines foreign key (loan_id) references BOOK_LOANS(loan_id);