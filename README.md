# Movie Recommender System
The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see how satisfied other users with similar traits are with movies of a certain category, you can choose a movie more easily. This program will show the average rating score of all movies belong to the given category by the given occupation of user's.

For this milestone, we implement JAVA code reading arguments, parsing movie dataset by genre, parsing movie dataset by occupation, and calculating average rating of occupation and genres.


## Building Dodker Image
This program will be executed on the Ubuntu20:04. Building docker image, the docker file will install 'openjdk-11-jdk' and 'maven' on the Ubuntu. Then 'run.sh' file will be migrated to the docker container.
```
docker build -t cse364-ubuntu20.04/movie_recommender_os .
docker run -it --name container cse364-ubuntu20.04/movie_recommender_os
```
------------------------------------------
## Getting Started
Clone and change directory to this repository, then compile via maven package manager:
```
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn install
```
Now, you can start this program with arguments:
```
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass [genres] [occupation]
```
Examples:
```
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "Comedy" "educator"
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "Comedy|Adventure" "doctor"
```

------------------------------------------
## User Guide
[genres]
- Enter the movie genres you want with double quotes -> Ex : "educator"
- You can put multiple genres seperated with '|' -> Ex : "Adventure|Comedy"
- Output is a rating average of all movies in the selected categories.
- If genres are not passed through as an argument, the program will be forced to exit.
-----------------------------
[occupation]
- Enter the occupation with double quotes -> Ex : "Documentary"
- Only one occupation is allowed.
- If you don't pass through an occupation as an argument or try to put multiple occupations, the program will be forced to exit.
-----------------------------
For user convenience, it supports various cases of input:
1. Case-insensitive<br/>
Ex. AdMiN => admin
2. Fixing a spacing error<br/>
Ex. a d m in => admin
3. In case that '-' is omitted or replaced by spacing<br/>
Ex. self employed => self-employed
----------------------------
The program will be terminated illegally with the phrase "Program was forced to exit." in the following cases: 
1. In case that there is invalid input
    - There are fewer or more than 2 input arguments.
    - The selected genres or occupation is not on a spcified list.
    - In case that the inputs are [occupation] [genres], not [genres] [occupation]
2. In case that there is no movie that belongs to all selected genres.
3. In case that there is no user that belongs to selected occupation.
----------------------------
With a vaild input, the output of this program:
```
Sum:  Count:  Average: 
```
Sample1:<br/>
  -Input:
  ```
  java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Adventure educator
  ```
  -Output:
  ```
  Sum: 34979 Count: 10215 Average: 3.424278022515908
  ```
  

Sample2:<br/>
  -Input:
  ```
  java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "Action|Comedy" doctor
  ```
  -Output:
  ```
  Sum: 4134 Count: 1178 Average: 3.5093378607809846
  ```


If there is no user with the selected occupation who watched a movie belonging to all selected movie genres, the output of this program:
```
Sum: 0 Count: 0 Average: 0
```

------------------------------------------
## Authors
-김형규: develops Userlist and Rating Calculator Classes<br/>
-문지헌: develops MainClass and writes README<br/>
-차준형: develops Arguments Class and Environment setup<br/>
-홍다빈: develops Movielist Class and build test cases
