#!/bin/bash
service mongod start
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mongoimport --type csv -d recommender -c link --headerline --columnsHaveTypes --drop ./resources/links.csv
mongoimport --type csv -d recommender -c review --headerline --drop ./resources/ratings.csv
mongoimport --type csv -d recommender -c movie --headerline --drop ./resources/movies_corrected.csv
mongoimport --type csv -d recommender -c poster --headerline --drop ./resources/movie_poster.csv
mongoimport --type csv -d recommender -c user --headerline --drop ./resources/users.csv
mvn spring-boot:run
