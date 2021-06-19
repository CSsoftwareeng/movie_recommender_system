#!/bin/bash
service mongod start
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mongoimport --type csv -d test -c link --headerline --columnsHaveTypes --drop ./resources/links.csv
mongoimport --type csv -d test -c review --headerline --drop ./resources/ratings.csv
mongoimport --type csv -d test -c movie --headerline --drop ./resources/movies_corrected.csv
mongoimport --type csv -d test -c poster --headerline --drop ./resources/movie_poster.csv
mongoimport --type csv -d test -c user --headerline --drop ./resources/users.csv
mongoimport --type csv -d test -c cache --headerline --columnsHaveTypes --drop ./resources/cache.csv
mvn spring-boot:run
