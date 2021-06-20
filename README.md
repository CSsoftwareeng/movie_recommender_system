# Movie Recommender System

The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see which movies other users with similar traits are most satisfied with or if you can see a list of movies that are similar to your favorite movies, you can choose a movie more easily. RecoMAX will show a poster of recommended movies based on your traits or your favorite movie.

## Algorithm

### Recommend Top 10 movies given user data

The program calculates the average movie rating of users who are similar to you and recommends 10 movies in the highest order(These processes are executed in rankUserBasedRating method in RatingCalculator.class). The program first calculates the average movie rating for users whose gender, age, and occupation all match with yours. If the list of movies does not reach 10, the program fills the rest of the list with the average movie ratings of users who match only two of them: gender, age, or occupation. If still don't have 10 movies, the program fills the rest of the list with the average movie ratings of users who match only one of their gender, age, or occupation. If it is not possible to create 10 movie lists from users similar to you, a movie list is created based on the average movie rating of all users(These processes are executed in searchSimilarUser method in UserList.class).<br/>

### Recommend movies given a movie title

The program calculates the average movie rating of similar movies and recommends movies in the highest order(These processes are executed in rankGenreBasedRating method in RatingCalculator.class). The program only uses data from users who rated a given movie above the average rating of each user(These processes are executed in searchFavoriteUsers method in UserList.class). And movies that match the genre of the given movie are recommended. Similar to recommending movies based on given user data, if the limit is not met, the number of matching genres is reduced by 1(These processes are executed in searchSimilarID method in MovieList.class).

---

## Building Docker Image

This program will be executed on the Ubuntu20:04. Building docker image, the docker file will install 'openjdk-11-jdk' and 'maven' on the Ubuntu. Then 'run.sh' file will be migrated to the docker container.

```sh
docker build -t cse364-ubuntu20.04/movie_recommender_os .
docker run -it --name container cse364-ubuntu20.04/movie_recommender_os
```

---

## Getting Started

Clone and change directory to this repository, then start spring-boot application:

```sh
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn spring-boot:run
```

Opening another console, now you can use **API** via **curl** with proper arguments:

```sh
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{"gender": [gender], "age": [age], "occupation": [occupation], "genres": [genres]}’ |json_pp
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{"gender": [gender], "age": [age], "occupation": [occupation]}’ |json_pp
```

Also, you can start this program(Recommend movies given a movie title and a number of movies to show) with arguments:

```sh
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{"title": [title], "limit": [limit]}’ |json_pp
```

Examples:

```sh
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender" : "", "age" : "", "occupation" : "", "genres" : "Romance|comedy"}' |json_pp
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "retired", "genres": ""}' |json_pp
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d '{"title": "Toy Story (1995)", "limit": 20}' |json_pp
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d '{"title": "Toy Story (1995)"}' |json_pp
```

Note:
If you compile with run.sh script file in container on top of local Windows machine,<br/>
you may need to replace 'new line character' properly with below command.

```sh
sed -i 's/\r$//' run.sh
```

---

## User Guide

When you access the RecoMAX homepage, you can see the main page as follows.
-메인화면 캡처
On the first screen of RecoMAX, you can check the 10 most popular movies including all genres and the 10 most popular movies in each of Action, Drama, and Animation genres. Under the RecoMAX logo you can find User Based Recommender and Movie Based Recommender tabs, and click to use each function.

### **User-Based Recommend System**
This is the first page of a user based movie recommender.
![userbased_main](https://user-images.githubusercontent.com/80080164/122674945-38419b00-d212-11eb-999e-2ddcf3d18baf.PNG)
You can select your gender, age, occupation and your favorite movie genres. Enter your age, and select your gender, occupation and genre through the drop down box. 



**[gender]**

Female | Male

**[occupation]**

|      other      |   academic    |     educator      |     artist     |   clerical    |      admin       |    college    |  grad student  | customer service |  doctor   |
| :-------------: | :-----------: | :---------------: | :------------: | :-----------: | :--------------: | :-----------: | :------------: | :--------------: | :-------: |
| **health care** | **executive** |  **managerial**   |   **farmer**   | **homemaker** | **K-12 student** |  **lawyer**   | **programmer** |   **retired**    | **sales** |
|  **marketing**  | **scientist** | **self-employed** | **technician** | **engineer**  |  **tradesman**   | **craftsman** | **unemployed** |    **writer**    |

**[genres]**

|    Action     | Adventure  |  Animation  | Children's  |   Comedy    |   Crime    | Documentary  |  Drama  |   Fantasy   |
| :-----------: | :--------: | :---------: | :---------: | :---------: | :--------: | :----------: | :-----: | :---------: |
| **Film-Noir** | **Horror** | **Musical** | **Mystery** | **Romance** | **Sci-Fi** | **Thriller** | **War** | **Western** |



### **Movie Based Recommend System**

This is an argument style guide when you request **GET** with **/movies/recommendandations**.

**[title]**

- Enter the full title including year
- Only one title is allowed.
- If you find movies based on movie title, you must enter movie title.
- Although the results are provided even if the year is not written, it is recommended to write the year with title for accurate results.

**[limit]**

- Enter the limit number in integer type.
- Limit should be positive integer.
- Only one limit is allowed.
- If you don't specify the **limit** field, the **limit** will set to 10 as a default.




---

## Authors

- 김형규: developed movie-based REST controller and wrote test codes
- 문지헌: wrote README
- 차준형: developed user-based REST controller and refactored error handlers
- 홍다빈: developed movie-based recommend system algorithms
