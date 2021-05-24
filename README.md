# Movie Recommender System
The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see movies which has similar traits to your favorite movie or if you can see a list of movies that are similar to your favorite movies, you can choose a movie more easily. This program will show a list of recommended movies based on your traits or your favorite movie.

## Algorithm
### Recommend Top 10 movies given user data
The program calculates the average movie rating of users who are similar to you and recommends 10 movies in the highest order(These processes are executed in rankUserBasedRating method in RatingCalculator.class). The program first calculates the average movie rating for users whose gender, age, and occupation all match with yours. If the list of movies does not reach 10, the program fills the rest of the list with the average movie ratings of users who match only two of them: gender, age, or occupation. If still don't have 10 movies, the program fills the rest of the list with the average movie ratings of users who match only one of their gender, age, or occupation. If it is not possible to create 10 movie lists from users similar to you, a movie list is created based on the average movie rating of all users(These processes are executed in searchSimilarUser method in UserList.class).<br/>
### Recommend movies given a movie title
The program calculates the average movie rating of similiar movies and recommends movies in the highest order(These processes are executed in rankGenreBasedRating method in RatingCalculator.class). The program only uses data from users who rated a given movie above the average rating of each user(These processes are executed in searchFavoriteUsers method in UserList.class). And movies that match the genre of the given movie are recommended. Similar to recommending movies based on given user data, if the limit is not met, the number of matching genres is reduced by 1(These processes are executed in searchSimilarID method in MovieList.class).

---

## Building Docker Image
This program will be executed on the Ubuntu20:04. Building docker image, the docker file will install 'openjdk-11-jdk' and 'maven' on the Ubuntu. Then 'run.sh' file will be migrated to the docker container.

```sh
docker build -t cse364-ubuntu20.04/movie_recommender_os .
docker run -it --name container cse364-ubuntu20.04/movie_recommender_os
```

---
## Getting Started
Clone and change directory to this repository, then start spring-boot aplication:

```sh
git clone https://github.com/CSsoftwareeng/movie_recommender_system.git
cd movie_recommender_system
mvn spring-boot:run
```

Now, you can start this program(for Recommend Top 10 movies given user data) with arguments:
```sh
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{"gender": [gender], "age": [age], "occupation": [occupation], "genre": [genres]}’ |json_pp
```
Also, you can start this program(Recommend movies given a movie title and a number of movies to show) with arguments:
```sh
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{"title": [title], "limit": [limit]}’ |json_pp
```

Examples:
```sh
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender" : "", "age" : "", "occupation" : "", "genre" : "Romance|comedy"}' |json_pp
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "retired", "genre": ""}' |json_pp
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{"title": "Toy Story (1995)", "limit": 20}’ |json_pp
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{"title": "Toy Story (1995)"}’ |json_pp
```

Note:
If you compile with run.sh script file in container on top of local Windows machine,<br/>
you may need to replace 'new line character' properly with below command.
```sh
sed -i 's/\r$//' run.sh
```

---

## User Guide
__[gender]__
- Enter the gender with double quotes -> Ex : "F" or "M"
- If gender is passed through as an empty string "", the program doesn't take gender into account when selecting a list of recommended movies.

---

__[age]__
- Enter the age with double quotes -> EX: "22"
- If age is passed through as an empty string "", the program doesn't take age into account when selecting a list of recommended movies.

---

__[occupation]__
- Enter the occupation with double quotes -> Ex : "educator"
- Only one occupation is allowed.
- If you pass through an occupation as an empty string "", the program doesn't take occupation into account when selecting a list of recommended movies.
- Occupation can be chosen from the following choices:<br/>

other | academic | educator | artist | clerical | admin | college | grad student | customer service | doctor
:---: | :---: | :---: | :---: |:---: |:---: |:---: |:---: |:---: |:---:
__health care__ | __executive__ | __managerial__ | __farmer__ | __homemaker__ | __K-12 student__ | __lawyer__ | __programmer__ | __retired__ | __sales__
__marketing__ | __scientist__ | __self-employed__ | __technician__ | __engineer__ | __tradesman__ | __craftsman__ | __unemployed__ | __writer__

---

__[genres]__
- Enter the movie genres you want with double quotes -> Ex : "Documentary"
- You can put multiple genres seperated with '|' -> Ex : "Adventure|Comedy"
- Output is a list of recommended movies that belong to at least one selected categories.
- If you pass through an genres as an empty string "", the program doesn't take genres into account when selecting a list of recommended movies.
- Genres can be chosen from the following choices:<br/>

Action | Adventure | Animation | Children's | Comedy | Crime | Documentary | Drama | Fantasy
:---: | :---: | :---: | :---: |:---: |:---: |:---: |:---: |:---:
__Film-Noir__ | __Horror__ | __Musical__ | __Mystery__ | __Romance__ | __Sci-Fi__ | __Thriller__ | __War__ | __Western__

---

__[title]__
- Enter the title with double quotes -> Ex : “Toy Story (1995)”
- Only one title is allowed.
- If you find movies based on movie title, you must enter movie title.
- Although the results are well provided even if the year is not written, it is recommended to write the year with title for accurate results.

---

__[limit]__
- Enter the number of list without double quotes -> Ex : 20
- Only one limit is allowed.
- If limit is not passed through as an argument, limit is set to 10 by default.

---

__For user convenience, it supports various cases of input:__
1. Case-insensitive<br/>
Ex. AdMiN => admin
2. Fixing a spacing error<br/>
Ex. a d m in => admin
3. In case that '-' is omitted or replaced by spacing<br/>
Ex. self employed => self-employed

---

__The program will be terminated illegally with the error message in the following cases:__ 
- ___For Recommend Top 10 movies given user data___
1. In case that the number of input arguments is not 4.<br/>
2. In case that the input arguments order is not correct.<br/>
3. In case that the gender is neither "F" nor "M".<br/>
4. In case that the age is a negative number.<br/>
5. In case that the selected genres or occupation is not on a spcified list.<br/>
6. In case that there is no movie that belongs to at least one selected genres.<br/>
- ___For Recommend movies given a movie title and a number of movies to show___
1. In case that the number of input arguments is not 1 or 2.<br/>
2. In case that the movie which has given title does not exist.<br/>
3. In case that the input arguments order is not correct.<br/>
4. In case that the limit is a negative number.<br/>

---

With a vaild input, the output of this program is a list of 10 or [limit] moives like:
```json
{
  "genres": "xxx",
  "imdb": "xxx",
  "title": "https://www.imdb.com/title/ttXXXXXXX"
}
```
Sample for Recommend Top 10 movies given user data:<br/>
  -Input:
  ```sh
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender" : "", "age" : "", "occupation" : "", "genre" : "Romance|comedy"}' |json_pp
  ```
  -Output:
  ```sh
{
      "genres" : "Documentary",
      "imdb" : "(http://www.imdb.com/title/tt0113147)",
      "title" : "Gate of Heavenly Peace, The (1995)"
   },
   {
      "genres" : "Drama",
      "imdb" : "(http://www.imdb.com/title/tt0114354)",
      "title" : "Schlafes Bruder (Brother of Sleep) (1995)"
   },
   {
      "genres" : "Comedy",
      "imdb" : "(http://www.imdb.com/title/tt0119139)",
      "title" : "Follow the Bitch (1998)"
   },
   {
      "genres" : "Adventure",
      "imdb" : "(http://www.imdb.com/title/tt0047630)",
      "title" : "Ulysses (Ulisse) (1954)"
   },
   {
      "genres" : "Comedy",
      "imdb" : "(http://www.imdb.com/title/tt0062281)",
      "title" : "Smashing Time (1967)"
   },
   {
      "genres" : "Horror",
      "imdb" : "(http://www.imdb.com/title/tt0069754)",
      "title" : "Baby, The (1973)"
   },
   {
      "genres" : "Drama",
      "imdb" : "(http://www.imdb.com/title/tt0028282)",
      "title" : "Song of Freedom (1936)"
   },
   {
      "genres" : "Comedy|Drama|Western",
      "imdb" : "(http://www.imdb.com/title/tt0070481)",
      "title" : "One Little Indian (1973)"
   },
   {
      "genres" : "Crime",
      "imdb" : "(http://www.imdb.com/title/tt0039589)",
      "title" : "Lured (1947)"
   },
   {
      "genres" : "Documentary",
      "imdb" : "(http://www.imdb.com/title/tt0168515)",
      "title" : "Bittersweet Motel (2000)"
   }
]
  ```
Sample for Recommend movies given a movie title:<br/>
  -Input:
  ```sh
 curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{"title": "Toy Story (1995)", "limit": 5}’ |json_pp
  ```
  -Output:
  ```sh
{
      "genres" : "Animation|Children's|Comedy",
      "imdb" : "(http://www.imdb.com/title/tt0120363)",
      "title" : "Toy Story 2 (1999)"
   },
   {
      "genres" : "Animation|Children's|Comedy",
      "imdb" : "(http://www.imdb.com/title/tt0120623)",
      "title" : "Bug's Life, A (1998)"
   },
   {
      "genres" : "Animation|Children's|Comedy",
      "imdb" : "(http://www.imdb.com/title/tt0120630)",
      "title" : "Chicken Run (2000)"
   },
   {
      "genres" : "Animation|Children's|Comedy|Musical",
      "imdb" : "(http://www.imdb.com/title/tt0827990)",
      "title" : "Aladdin (1992)"
   },
   {
      "genres" : "Animation|Children's|Comedy|Musical",
      "imdb" : "(http://www.imdb.com/title/tt0061852)",
      "title" : "Jungle Book, The (1967)"
   }
  ```

------------------------------------------
## Authors
-김형규: developed movie-based REST controller and wrote test codes<br/>
-문지헌: write README<br/>
-차준형: developed user-based REST controller and refactored error handlers<br/>
-홍다빈: developed movie-based recommend system algorithms
