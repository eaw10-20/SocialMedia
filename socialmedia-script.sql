

--CREATE TABLE STATEMENTS
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY
	,	user_fname varchar(100)
	,	user_lname varchar(100)
	,	user_email varchar(150) UNIQUE 
	,	user_password varchar(150)
	,	user_username varchar(150) UNIQUE 
	, 	user_avatar varchar(100)
)


CREATE TABLE post (
	user_id int
	,	post_id SERIAL PRIMARY KEY
	,	post_description varchar(255)
	, 	CONSTRAINT user_post_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  
)


CREATE TABLE likes (
	like_id SERIAL PRIMARY KEY
	,	user_id int
	,	post_id int
	, 	CONSTRAINT like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  
	,	CONSTRAINT like_post_id FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE  
);


CREATE TABLE photos (
	photo_id SERIAL PRIMARY KEY
	,	post_id int
	,	photobyte varchar(150)
	,	CONSTRAINT photo_post_id FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE
);

--DELECT STATEMENTS
DROP TABLE photos;
DROP TABLE likes;
DROP TABLE post;
DROP TABLE users;


--SELECT STATEMENTS
SELECT * FROM users;
SELECT * FROM user_post;
SELECT * FROM likes;


--INSERT STATEMENTS
INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) 
VALUES('John', 'Frank', 'john@email.com', '1234', 'newbie', 'avatarurl');

INSERT INTO post (user_id, post_description) 
VALUES (1, 'WHOAHOAHOA');

INSERT INTO likes(user_id, post_id) 
VALUES (1,1);

--UPDATE STATEMENTS
UPDATE users 
SET user_avatar = 'WOW.jpeg'
WHERE user_id = 1;





