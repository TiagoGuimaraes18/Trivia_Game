-- Create Data Base
Create Database IF NOT EXISTS trivia_game;
Use trivia_game;

-- Users Table
Create Table IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    answers INT NOT NULL,
    correct INT NOT NULL,
    incorrect INT NOT NULL,
    victory INT NOT NULL
    );

-- Categories Table
Create Table IF NOT EXISTS categories(
    id INT PRIMARY KEY NOT NULL,
    category VARCHAR(100) NOT NULL);

-- Levels Table
Create Table IF NOT EXISTS levels(
    id INT PRIMARY KEY NOT NULL,
    levelsname VARCHAR(100) NOT NULL);

-- Questions Table
Create Table IF NOT EXISTS questions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    questionText TEXT NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL,
    category_id INT NOT NULL	,
    levels_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (levels_id) REFERENCES levels(id)
    );

-- Add Categories
INSERT INTO categories (id,category) VALUES 
(1, 'Science'),
(2, 'History'),
(3, 'Geography'),
(4, 'Technology'),
(5, 'Sports');

-- Add levels
INSERT INTO levels (id,levelsname) VALUES
(1,'Easy'),
(2,'Medium'),
(3,'Hard');

-- Add questions 
LOAD DATA LOCAL INFILE '/Trivia Game/resources/questions.csv'
INTO table questions
FIELDS TERMINATED BY ','
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(category_id, levels_id, questionText, option_a, option_b, option_c, option_d, correct_option);










