# Movie Recommender System

The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see movies which has similar traits to your favorite movie or if you can see a list of movies that are similar to your favorite movies, you can choose a movie more easily. This program will show a list of recommended movies based on your traits or your favorite movie.

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

When you pass arguments to body parameter, you should take care followings.

### **User-Based Recommend System**

This is an argument style guide when you request **GET** with **/users/recommendandations**.

**[gender]**

- Enter the gender with double quotes -> Ex : "F" or "M"
- If gender is passed through as an empty string "", the program doesn't take gender into account when selecting a list of recommended movies.

**[age]**

- Enter the age with double quotes -> EX: "22"
- If age is passed through as an empty string "", the program doesn't take age into account when selecting a list of recommended movies.

**[occupation]**

- Enter the occupation with double quotes -> Ex : "educator"
- Only one occupation is allowed.
- If you pass through an occupation as an empty string "", the program doesn't take occupation into account when selecting a list of recommended movies.
- Occupation can be chosen from the following choices:<br/>

|      other      |   academic    |     educator      |     artist     |   clerical    |      admin       |    college    |  grad student  | customer service |  doctor   |
| :-------------: | :-----------: | :---------------: | :------------: | :-----------: | :--------------: | :-----------: | :------------: | :--------------: | :-------: |
| **health care** | **executive** |  **managerial**   |   **farmer**   | **homemaker** | **K-12 student** |  **lawyer**   | **programmer** |   **retired**    | **sales** |
|  **marketing**  | **scientist** | **self-employed** | **technician** | **engineer**  |  **tradesman**   | **craftsman** | **unemployed** |    **writer**    |

**[genres]**

- Enter the movie genres you want with double quotes -> Ex : "Documentary"
- You can put multiple genres separated with '|' -> Ex : "Adventure|Comedy"
- Output is a list of recommended movies that belong to at least one selected categories.
- If you pass through an genre as an empty string "", the program doesn't take genres into account when selecting a list of recommended movies.
- Genres can be chosen from the following choices:<br/>

|    Action     | Adventure  |  Animation  | Children's  |   Comedy    |   Crime    | Documentary  |  Drama  |   Fantasy   |
| :-----------: | :--------: | :---------: | :---------: | :---------: | :--------: | :----------: | :-----: | :---------: |
| **Film-Noir** | **Horror** | **Musical** | **Mystery** | **Romance** | **Sci-Fi** | **Thriller** | **War** | **Western** |

**Body Parameter Examples :**

```json
{
  "gender": "F",
  "age": "25",
  "occupation": "educator",
  "genres": "Adventure|Comedy"
}
```

---

### **Movie Based Recommend System**

This is an argument style guide when you request **GET** with **/movies/recommendandations**.

**[title]**

- Enter the full title including year
- Only one title is allowed.
- If you find movies based on movie title, you must enter movie title.
- Although the results are provided even if the year is not written, it is recommended to write the year with title for accurate results.

**[limit]**

- Enter the limit number in integer type
- Only one limit is allowed.
- If you don't specify the **limit** field, the **limit** will set to 10 as a default.

**Body Parameter Examples :**

```json
{
  "title": "Toy Story (1995)"
}
```

```json
{
  "title": "Toy Story",
  "limit": 15
}
```

---

### **For user convenience, following cases are acceptable as arguments:**

      1. Case-insensitive
      2. Fixing a spacing error
      3. In case that '-' is omitted or replaced by spacing

For example, both cases shown below will yield the same outputs.

```json
{
  "gender": "F",
  "age": "25",
  "occupation": "self-employed",
  "genres": "Sci-Fi|Action"
}
```

```json
{
  "gender": "f",
  "age": "25",
  "occupation": "SELF EMPLOYED",
  "genres": "sci FI| a c t i o n"
}
```

---

**The API will return response code without expected results if:**

### 1. the number of arguments is less than 3 when you request **/users/recomendations**.

- input

```sh
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "F", "age": "15"}'|json_pp
```

- output

```json
{
  "error": "Bad Request",
  "message": "[ERROR : ArgCntError] 2 is invalid number of arguments.",
  "path": "/users/recommendations",
  "status": 400,
  "timestamp": "2021-05-24T16:08:07.666+00:00"
}
```

### 2. the name of fields is wrong.

- input

```sh
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"movie_name": "Toy Story", "limit": 15}'|json_pp
```

- output

```json
{
  "error": "Bad Request",
  "message": "[ERROR : ArgMissingError] The argument 'title' is missing.",
  "path": "/movies/recommendations",
  "status": 400,
  "timestamp": "2021-05-24T16:12:18.195+00:00"
}
```

### 3. the movie title that is given does not exist.

- input

```sh
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "NOT_EXISTING_MOVIE", "limit": 15}'|json_pp
```

- output

```json
{
  "error": "Not Found",
  "message": "[ERROR : MovieNotExistError] Can't find a movie titled: NOT_EXISTING_MOVIE",
  "path": "/movies/recommendations",
  "status": 404,
  "timestamp": "2021-05-24T16:14:19.183+00:00"
}
```

### 4. the invalid arguments are put as specified in **User Guide**.

```sh
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "F", "age": "15", "occupation":"NOT_EXISTING_JOB"}'|json_pp
```

- output

```json
{
  "error": "Bad Request",
  "message": "[ERROR : ArgNotExistError] Can't find [NOT_EXISTING_JOB] in the available occupation list.",
  "path": "/users/recommendations",
  "status": 400,
  "timestamp": "2021-05-24T16:22:23.370+00:00"
}
```

---

With a valid input, the output of this program is a list of 10 or [limit] moives like:

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
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender" : "", "age" : "", "occupation" : "", "genres" : "Romance|comedy"}' | json_pp
```

-Output:

```json
[
  {
    "genres": "Comedy",
    "imdb": "(http://www.imdb.com/title/tt0062281)",
    "title": "Smashing Time (1967)"
  },
  {
    "genres": "Comedy|Drama|Western",
    "imdb": "(http://www.imdb.com/title/tt0070481)",
    "title": "One Little Indian (1973)"
  },
  {
    "genres": "Comedy",
    "imdb": "(http://www.imdb.com/title/tt0119139)",
    "title": "Follow the Bitch (1998)"
  },
  {
    "genres": "Animation|Comedy|Thriller",
    "imdb": "(http://www.imdb.com/title/tt0112691)",
    "title": "Close Shave, A (1995)"
  },
  {
    "genres": "Animation|Comedy",
    "imdb": "(http://www.imdb.com/title/tt0108598)",
    "title": "Wrong Trousers, The (1993)"
  },
  {
    "genres": "Drama|Romance",
    "imdb": "(http://www.imdb.com/title/tt0209322)",
    "title": "Skipped Parts (2000)"
  },
  {
    "genres": "Drama|Romance|War",
    "imdb": "(http://www.imdb.com/title/tt0034583)",
    "title": "Casablanca (1942)"
  },
  {
    "genres": "Comedy|Drama|Western",
    "imdb": "(http://www.imdb.com/title/tt0055630)",
    "title": "Yojimbo (1961)"
  },
  {
    "genres": "Comedy|Drama|Romance",
    "imdb": "(http://www.imdb.com/title/tt0021749)",
    "title": "City Lights (1931)"
  },
  {
    "genres": "Comedy",
    "imdb": "(http://www.imdb.com/title/tt0017925)",
    "title": "General, The (1927)"
  }
]
```

Sample for Recommend movies given a movie title:<br/>
-Input:

```sh
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "Toy Story (1995)", "limit": 5}' |json_pp
```

-Output:

```json
[
  {
    "genres": "Animation|Children's|Comedy",
    "imdb": "(http://www.imdb.com/title/tt0120363)",
    "title": "Toy Story 2 (1999)"
  },
  {
    "genres": "Animation|Children's|Comedy",
    "imdb": "(http://www.imdb.com/title/tt0120623)",
    "title": "Bug's Life, A (1998)"
  },
  {
    "genres": "Animation|Children's|Comedy",
    "imdb": "(http://www.imdb.com/title/tt0120630)",
    "title": "Chicken Run (2000)"
  },
  {
    "genres": "Animation|Children's|Comedy|Musical",
    "imdb": "(http://www.imdb.com/title/tt0827990)",
    "title": "Aladdin (1992)"
  },
  {
    "genres": "Animation|Children's|Comedy|Musical",
    "imdb": "(http://www.imdb.com/title/tt0061852)",
    "title": "Jungle Book, The (1967)"
  }
]
```

---

## Authors

- 김형규: developed movie-based REST controller and wrote test codes
- 문지헌: wrote README
- 차준형: developed user-based REST controller and refactored error handlers
- 홍다빈: developed movie-based recommend system algorithms
