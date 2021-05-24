# Movie Recommender System
The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see movies which has similar traits to your favorite movie, you can choose a movie more easily. This program will show a list of recommended movies based on your favorite movie.


## Algorithm
The program calculates the average movie rating of a movie similiar to a given movie for users who have the movie rating above average movie rating by each user and recommends the number of [limit] movies in the highest order(These processes are executed in RatingCalculator.class).
The program classifies movies with similar genres(These processes are executed in MovieList.class). Then, the average of the ratings for each user is calculated. Only users who gave the movie an above-average rating of each user are listed(These processes are executed in UserList.class).
The program first prints the movie whose genres all match with given movie. If the list of movies does not reach [limit], the program fills the rest of the list with the movie whose matches less than one of them. If still don't have [limit] movies, the program fills the rest of the list with the movie whose matches less than two of them. If it is not possible to create [limit] movie lists from matched genres, a movie list is created based on the average movie rating of all movies.
------------------------------------------
## Building Docker Image
This program will be executed on the Ubuntu20:04. Building docker image, the docker file will install 'openjdk-11-jdk' and 'maven' on the Ubuntu. Then 'run.sh' file will be migrated to the docker container.
```
docker build -t cse364-ubuntu20.04/movie_recommender_os .
docker run -it --name container cse364-ubuntu20.04/movie_recommender_os
```
------------------------------------------
## Getting Started
Clone and change directory to this repository, then start spring-boot aplication:
```
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn spring-boot:run
```
Now, you can start this program with arguments:
```
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: [title], “limit”: [limit]}’
```


Examples:
```
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: “Toy Story (1995)”, “limit”: 20}’
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: “Toy Story (1995)”}’
```
Note:
If you compile with run.sh script file in container on top of local Windows machine,<br/>
you may need to replace 'new line character' properly with below command.
```
sed -i 's/\r$//' run.sh
```
------------------------------------------
## User Guide
__[title]__
- Enter the title with double quotes -> Ex : “Toy Story (1995)”
- Only one title is allowed.
- If you find movies based on movie title, you must enter movie title.
- Although the results are well provided even if the year is not written, it is recommended to write the year with title for accurate results.
-----------------------------
__[limit]__
- Enter the title with double quotes -> Ex : “20”
- Only one limit is allowed.
- If you pass through an limit as an empty string "", the program takes limit to "10".
-----------------------------
__For user convenience, it supports various cases of input:__
1. Case-insensitive<br/>
Ex. AdMiN => admin
2. Fixing a spacing error<br/>
Ex. a d m in => admin
3. In case that '-' is omitted or replaced by spacing<br/>
Ex. self employed => self-employed
----------------------------
__The program will be terminated illegally with the phrase "Program was forced to exit." in the following cases:__ 
1. In case that there are fewer than 1 or more than 2 input arguments.<br/>
2. In case that the movie who has given title does not exist.<br/>
3. In case that the title is not inputted.<br/>
4. In case that the input arguments order is not correct.<br/>
----------------------------
With a vaild input, the output of this program is a list of [limit] moives like:
```
{
  “title”: “xxx”,
  “genres”: “xxx”,
  “imdb”: “https://www.imdb.com/title/ttXXXXXXX”
}
```
Sample1:<br/>
  -Input:
  ```

  ```
  -Output:
  ```

  ```
Sample2:<br/>
  -Input:
  ```
 
  ```
  -Output:
  ```

  ```

------------------------------------------
## Authors
-김형규: develops Userlist class and refactoring Error Classes<br/>
-문지헌: write README<br/>
-차준형: develops Arguments class and MainClass<br/>
-홍다빈: develops Movielist class and 
