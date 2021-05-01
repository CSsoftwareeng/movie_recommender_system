# Movie Recommender System
The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see which movies other users with similar traits are most satisfied with, you can choose a movie more easily. This program will show a list of top 10 recommended movies based on given gender, age, and occupation.


## Algorithm
The program calculates the average movie rating of users who are similar to you and recommends 10 movies in the highest order. This program prints the average rating together, so that if the average rating is the same, the user knows that it is recommended as the same priorit.
The program first calculates the average movie rating for users whose gender, age, and occupation all match with yours. If the list of movies does not reach 10, the program fills the rest of the list with the average movie ratings of users who match only two of them: gender, age, or occupation. If still don't have 10 movie, the program fills the rest of the list with the average movie ratings of users who match only one of their gender, age, or occupation. If it is not possible to create 10 movie lists from users similar to you, a movie list is created based on the average movie rating of all users.

------------------------------------------
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
java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass [gender] [age] [occupation] [genres]
```
Examples:
```
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass “F” “22” “Grad student”
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass “M” “45” “doctor” “Action|Comedy”
Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass “” “” “”
```
Note:
If you compile with run.sh script file in container on top of local Windows machine,<br/>
you may need to replace 'new line character' properly with below command.
```
sed -i 's/\r$//' run.sh
```
------------------------------------------
## User Guide
__[gender]__
- Enter the gender with double quotes -> Ex : "F" or "M"
- If gender is not passed through as an argument, the program doesn't take gender into account when selecting a list of recommended movies.
-----------------------------
__[age]__
- Enter the age with double quotes -> EX: "22"
- If age is not passed through as an argument, the program doesn't take age into account when selecting a list of recommended movies.
-----------------------------
__[occupation]__
- Enter the occupation with double quotes -> Ex : "educator"
- Only one occupation is allowed.
- If you don't pass through an occupation as an argument, the program doesn't take occupation into account when selecting a list of recommended movies.
- Occupation can be chosen from the following choices:<br/>

other | academic | educator | artist | clerical | admin | college | grad student | customer service | doctor
:---: | :---: | :---: | :---: |:---: |:---: |:---: |:---: |:---: |:---:
__health care__ | __executive__ | __managerial__ | __farmer__ | __homemaker__ | __K-12 student__ | __lawyer__ | __programmer__ | __retired__ | __sales__
__marketing__ | __scientist__ | __self-employed__ | __technician__ | __engineer__ | __tradesman__ | __craftsman__ | __unemployed__ | __writer__

-----------------------------
__[genres]__
- Enter the movie genres you want with double quotes -> Ex : "Documentary"
- You can put multiple genres seperated with '|' -> Ex : "Adventure|Comedy"
- Output is a list of recommended movies that belong to at least one selected categories.
- If you don't pass through an genres as an argument, the program doesn't take genres into account when selecting a list of recommended movies
- Genres can be chosen from the following choices:<br/>

Action | Adventure | Animation | Children's | Comedy | Crime | Documentary | Drama | Fantasy
:---: | :---: | :---: | :---: |:---: |:---: |:---: |:---: |:---:
__Film-Noir__ | __Horror__ | __Musical__ | __Mystery__ | __Romance__ | __Sci-Fi__ | __Thriller__ | __War__ | __Western__

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
1. In case that there are fewer than 3 or more than 4 input arguments.<br/>
If you do not want to select more than one of gender, age, or occupation, please use empty double quotes. In the case of the genre, you do not need to enter empty double quotes. The input must consist of 3 or 4 arguments, including empty double quotes.
2. In case that the age is a negative number.<br/>
3. In case that the selected genres or occupation is not on a spcified list.<br/>
4. In case that the input arguments order is not [gender] [age] [occupation] [genres]<br/>
5. In case that there is no movie that belongs to at least one selected genres.<br/>
----------------------------
With a vaild input, the output of this program is a list of 10 moives like:
```
Movie Name (year) (http://www.imdb.com/title/ttimdbID) Rating:
```
Sample1:<br/>
  -Input:
  ```
  java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "M" "30" "doctor" "Children's"
  ```
  -Output:
  ```
  Wide Awake (1998) (http://www.imdb.com/title/tt0120510)Rating : 5.0
  Watership Down (1978) (http://www.imdb.com/title/tt0078480)Rating : 5.0
  Madeline (1998) (http://www.imdb.com/title/tt0123987)Rating : 5.0
  Winnie the Pooh and the Blustery Day (1968) (http://www.imdb.com/title/tt0063819)Rating : 5.0
  Sleeping Beauty (1959) (http://www.imdb.com/title/tt0053285)Rating : 4.666666666666667
  Batman: Mask of the Phantasm (1993) (http://www.imdb.com/title/tt0106364)Rating : 4.666666666666667
  Cinderella (1950) (http://www.imdb.com/title/tt0042332)Rating : 4.666666666666667
  Toy Story 2 (1999) (http://www.imdb.com/title/tt0120363)Rating : 4.6
  Something Wicked This Way Comes (1983) (http://www.imdb.com/title/tt0086336)Rating : 4.5
  Gulliver's Travels (1939) (http://www.imdb.com/title/tt0031397)Rating : 4.5
  ```
Sample2:<br/>
  -Input:
  ```
  java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass "" "15" "" "Sci-Fi"
  ```
  -Output:
  ```
  Alphaville (1965) (http://www.imdb.com/title/tt0058898)Rating : 5.0
  Metropolis (1926) (http://www.imdb.com/title/tt0017136)Rating : 4.888888888888889
  Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb (1963) (http://www.imdb.com/title/tt0057012)Rating : 4.514285714285714
  Cube (1997) (http://www.imdb.com/title/tt0123755)Rating : 4.5
  Visitors, The (Les Visiteurs) (1993) (http://www.imdb.com/title/tt0108500)Rating : 4.5
  Omega Man, The (1971) (http://www.imdb.com/title/tt0067525)Rating : 4.5
  Battle for the Planet of the Apes (1973) (http://www.imdb.com/title/tt0069768)Rating : 4.5
  Matrix, The (1999) (http://www.imdb.com/title/tt0133093)Rating : 4.4646464646464645
  Clockwork Orange, A (1971) (http://www.imdb.com/title/tt0066921)Rating : 4.454545454545454
  Brazil (1985) (http://www.imdb.com/title/tt0088846)Rating : 4.368421052631579
  ```

------------------------------------------
## Authors
-김형규: develops Userlist class and refactoring Error Classes<br/>
-문지헌: develops RatingCalculator Class<br/>
-차준형: develops Arguments class and MainClass<br/>
-홍다빈: develops Movielist class and write README
