DROP DATABASE IF EXISTS TestingSystem;
CREATE DATABASE TestingSystem;
USE TestingSystem;

CREATE TABLE `Account`(
	AccountID			SMALLINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Email				VARCHAR(50) UNIQUE KEY NOT NULL,
    Username			VARCHAR(50) UNIQUE KEY NOT NULL,
    lastname			VARCHAR(50)  NOT NULL,
    firstname			VARCHAR(50) NOT NULL,
    `password`			VARCHAR(250) NOT NULL,
    CreateDate			DATE DEFAULT(now())
);

CREATE TABLE `Group`(
	GroupID				TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    GroupName			VARCHAR(100) UNIQUE KEY NOT NULL,
    CreatorID			SMALLINT UNSIGNED,
    CreateDate			DATE DEFAULT(now()),
    FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID) ON DELETE CASCADE
);

-- password: 123456
INSERT INTO `Account`(Email 					, Username		, `password`														, 	firstname			,	lastname		, 	CreateDate)
VALUE 				('account1@gmail.com'		,'xuandung1'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Xuan'		,	'A'				,  '2023-10-21'),
					('account2@gmail.com'		,'xuandung2'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Xuan'		,	'B'				,  '2023-10-22'),
                    ('account3@gmail.com'		,'xuandung3'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Xuan'		,	'C'				,  '2023-10-23'),
                    ('account4@gmail.com'		,'xuandung4'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Xuan'		,	'D'				,  '2023-10-24');

-- Thêm dữ liệu cho bảng `Group`
INSERT INTO `Group`	(  GroupName		, CreatorID		, CreateDate)
VALUE 				('Group 1'			,   1			,'2023-10-17'),
					('Group 2'			,   1			,'2023-10-18'),
                    ('Group 3'			,   2			,'2023-10-19'),
                    ('Group 4'			,   3			,'2023-10-20'),
                    ('Group 5'			,   4			,'2023-10-21'),
                    ('Group 6'			,   2			,'2023-10-22'),
                    ('Group 7'			,   2			,'2023-10-23'),
                    ('Group 8'			,   3			,'2023-10-24');

                            
                            
                            
                            