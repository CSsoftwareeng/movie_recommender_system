#!/bin/bash

# Your run.sh must include command lines that git clone your_repository, 
# cd your_repository, mvn instll, and java command to run your code. 
# The details are given in Submission Instructions.

# git clone your_repository
# cd your_repository_name
# mvn install
# java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar xxx.yyy.YourClass Adventure educator

git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn install
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Adventure educator
