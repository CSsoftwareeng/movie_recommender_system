# Movie Recommender System
The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see how satisfied other users with similar traits are with movies of a certain category, you can choose a movie more easily. This program will show the average rating score of all movies belong to the given category by the given occupation of user's.

For this milestone, we implement JAVA code reading arguments, parsing movie dataset by genre, parsing movie dataset by occupation, and calculating average rating of occupation and genres.


## Installation
    docker build -t cse364-ubuntu20.04/movie_recommender_os .
    docker run -it --name container cse364-ubuntu20.04/movie_recommender_os

------------------------------------------
## Getting Started
Clone this repository, then enter the main didrectory and install maven:
```
    git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
    cd movie_recommender_system
    mvn install
```
Now, you can start this program:
```
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass [genres] [occupation]
```
Examples:
```
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Comedy educator
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Comedy|Action doctor
```

------------------------------------------
## User Guide
[genres]
- Enter the movie genres you want
- The genre input can be one or more genres with '|'.
- Output is a rating average of all movies in the selected categories.
- If genres are not selected, this program will terminate.
-----------------------------
[occupation]
- Enter the occupation.
- Only one occupation is allowed.
- With fewer or more input occupations, this program will terminate.
-----------------------------
For user convenience, it supports various cases of input:
1. Case-insensitive
    Ex. AdMiN => admin
2. Fixing a spacing error
> Ex. a d m in => admin
3. In case that '-' is omitted or replaced by spacing
> Ex. self employed => self-employed
----------------------------
This program terminates with the phrase "Program was forced to exit." in the following cases:
1. In case that there is invalid input
    - There are fewer or more than 2 input arguments.
    - The selected genres or occupation is not on a spcified list.
    - In case that the inputs are [occupation] [genres], not [genres] [occupation]
2. In case that there is no movie that belongs to all selected genres.
3. In case that there is no user that belongs to selected occupation.
----------------------------
With a vaild input, the output of this program:
    Sum:  Count:  Average: 
```
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Adventure educator
Sum: 34979 Count: 10215 Average: 3.424278
```

If there is no user with the selected occupation who watched a movie belonging to all inputted movie genres, the output of this program:
```
Sum: 0 Count: 0 Average: 0
```

------------------------------------------
## Authors
-김형규: develops Userlist and Rating Calculator Classes

-문지헌: develops MainClass and writes README

-차준형: develops Arguments Class and Environment setup

-홍다빈: develops Movielist Class and build test cases
