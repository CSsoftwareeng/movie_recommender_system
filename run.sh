#!/bin/bash

git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn package
java -jar target/cse364-project-1-1.0-SNAPSHOT.jar
