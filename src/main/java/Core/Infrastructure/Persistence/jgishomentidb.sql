-- Drop tables if they exist
DROP TABLE IF EXISTS user_achievements;
DROP TABLE IF EXISTS user_quiz_scores;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS quizes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS achievement_desc;

CREATE TABLE users(
	user_id 	int AUTO_INCREMENT PRIMARY KEY,
	user_name 	varchar(25) NOT NULL,
	password	varchar(40) NOT NULL,
	profile_picture varchar(255) DEFAULT "pictures/default.png"
);


CREATE TABLE quizes(
	quiz_id		int AUTO_INCREMENT PRIMARY KEY,
	creator_id 	int,
	-- Constraints
	FOREIGN KEY (creator_id) REFERENCES users(user_id)
);

CREATE TABLE questions(
	question_id	int AUTO_INCREMENT PRIMARY KEY,
	quiz_id		int,
	question_text	varchar(255) NOT NULL,
	question_image	varchar(255), -- if empty then display no image 
	points		int NOT NULL, -- how many points you get for getting the question right
	-- Constraints
	FOREIGN KEY (quiz_id) REFERENCES quizes(quiz_id)
);

CREATE TABLE answers(
	answer_id 	int AUTO_INCREMENT PRIMARY KEY,
	question_id 	int, 			-- add foreign key constraint
	answer_text	varchar(255), 		-- option
	question_type	varchar(1),
	-- Constraints
	check (question_type in ('C','I')), 	-- Either 'C' or 'I' (Correct / Incorrect)
	FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE user_quiz_scores(
	user_id		int,
	quiz_id		int,
	best_score	int,
	last_score	int,
	PRIMARY KEY (user_id, quiz_id),
    	FOREIGN KEY (user_id) REFERENCES users(user_id),
    	FOREIGN KEY (quiz_id) REFERENCES quizes(quiz_id)	
);


CREATE TABLE achievement_desc(
	achievement_id		int AUTO_INCREMENT PRIMARY KEY,
	achievement_name	varchar(255),
	achievement_desc	varchar(255),
	achievement_img		varchar(255)
);

-- Achievements
CREATE TABLE user_achievements(
	user_id 		int, -- foreign key
	achievement_id 		int,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
    	FOREIGN KEY (achievement_id) REFERENCES achievement_desc(achievement_id)
);

-- TODO: ADD FRIENDS

