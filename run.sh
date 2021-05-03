#!/bin/bash

git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn install
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "F" "25" "Grad student" "Action|Comedy"
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "M" "30" "Athletes" "Children’s"
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "F" "" "retired" "Animation|Drama|Fantasy" 
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "" "15" "" "Sci-Fi"
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "" "" "" "Romance|Comedy"


