# Movie Recommender System

The movie platform system supports a great multitude of movies, so it is not easy to choose which movie to watch. At this point, if you can see movies which has similar traits to your favorite movie, you can choose a movie more easily. This program will show a list of recommended movies based on your favorite movie.
Meanwhile, if you can see which movies other users with similar traits are most satisfied with, you can choose a movie more easily. This program will show a list of top 10 recommended movies based on given gender, age, and occupation.

## Algorithm

The program calculates the average movie rating of a movie similiar to a given movie for users who have the movie rating above average movie rating by each user and recommends the number of [limit] movies in the highest order(These processes are executed in RatingCalculator.class).
The program classifies movies with similar genres(These processes are executed in MovieList.class). Then, the average of the ratings for each user is calculated. Only users who gave the movie an above-average rating of each user are listed(These processes are executed in UserList.class).
The program first prints the movie whose genres all match with given movie. If the list of movies does not reach [limit], the program fills the rest of the list with the movie whose matches less than one of them. If still don't have [limit] movies, the program fills the rest of the list with the movie whose matches less than two of them. If it is not possible to create [limit] movie lists from matched genres, a movie list is created based on the average movie rating of all movies.
Also, the program calculates the average movie rating of users who are similar to you and recommends 10 movies in the highest order(These processes are executed in RatingCalculator.class). This program prints the average rating together, so that if the average rating is the same, the user knows that it is recommended as the same priority. The program first calculates the average movie rating for users whose gender, age, and occupation all match with yours. If the list of movies does not reach 10, the program fills the rest of the list with the average movie ratings of users who match only two of them: gender, age, or occupation. If still don't have 10 movies, the program fills the rest of the list with the average movie ratings of users who match only one of their gender, age, or occupation. If it is not possible to create 10 movie lists from users similar to you, a movie list is created based on the average movie rating of all users(These processes are executed in UserList.class).

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

Now, you can start this program with arguments:

```sh
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: [title], “limit”: [limit]}’
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{“gender”: [gender], “age”: [age], “occupation”: [occupation], “genre”: [genres]}’
```

Examples:

```sh
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: “Toy Story (1995)”, “limit”: 20}’
curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: “Toy Story (1995)”}’
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{“gender”: “F”, “age”: “25”, “occupation”: “Grad student”, “genre”: “Action|War”}’
curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{“gender”: “M”, “age”: “”, “occupation”: “retired”, “genres”: “”}’

```

Note:
If you compile with run.sh script file in container on top of local Windows machine,<br/>
you may need to replace 'new line character' properly with below command.

```sh
sed -i 's/\r$//' run.sh
```

---

## User Guide

**[gender]**

- Enter the gender with double quotes -> Ex : "F" or "M"
- If gender is passed through as an empty string "", the program doesn't take gender into account when selecting a list of recommended movies.

---

**[age]**

- Enter the age with double quotes -> EX: "22"
- If age is passed through as an empty string "", the program doesn't take age into account when selecting a list of recommended movies.

---

**[occupation]**

- Enter the occupation with double quotes -> Ex : "educator"
- Only one occupation is allowed.
- If you pass through an occupation as an empty string "", the program doesn't take occupation into account when selecting a list of recommended movies.
- Occupation can be chosen from the following choices:<br/>

|      other      |   academic    |     educator      |     artist     |   clerical    |      admin       |    college    |  grad student  | customer service |  doctor   |
| :-------------: | :-----------: | :---------------: | :------------: | :-----------: | :--------------: | :-----------: | :------------: | :--------------: | :-------: |
| **health care** | **executive** |  **managerial**   |   **farmer**   | **homemaker** | **K-12 student** |  **lawyer**   | **programmer** |   **retired**    | **sales** |
|  **marketing**  | **scientist** | **self-employed** | **technician** | **engineer**  |  **tradesman**   | **craftsman** | **unemployed** |    **writer**    |

---

**[genres]**

- Enter the movie genres you want with double quotes -> Ex : "Documentary"
- You can put multiple genres seperated with '|' -> Ex : "Adventure|Comedy"
- Output is a list of recommended movies that belong to at least one selected categories.
- If you pass through an genres as an empty string "", the program doesn't take genres into account when selecting a list of recommended movies.
- Genres can be chosen from the following choices:<br/>

|    Action     | Adventure  |  Animation  | Children's  |   Comedy    |   Crime    | Documentary  |  Drama  |   Fantasy   |
| :-----------: | :--------: | :---------: | :---------: | :---------: | :--------: | :----------: | :-----: | :---------: |
| **Film-Noir** | **Horror** | **Musical** | **Mystery** | **Romance** | **Sci-Fi** | **Thriller** | **War** | **Western** |

---

**[title]**

- Enter the title with double quotes -> Ex : “Toy Story (1995)”
- Only one title is allowed.
- If you find movies based on movie title, you must enter movie title.
- Although the results are well provided even if the year is not written, it is recommended to write the year with title for accurate results.

---

**[limit]**

- Enter the number of list without double quotes -> Ex : 20
- Only one limit is allowed.
- If you pass through limit, the program takes limit to 10.

---

**For user convenience, it supports various cases of input:**

1. Case-insensitive<br/>
   Ex. AdMiN => admin
2. Fixing a spacing error<br/>
   Ex. a d m in => admin
3. In case that '-' is omitted or replaced by spacing<br/>
   Ex. self employed => self-employed

---

**The program will be terminated illegally with the error message in the following cases:**

1. In case that there are 1,2 or 4 input arguments.<br/>
   If there are 1,2 input arguments, this program recommends movies based on movie title. If you pass through limit, the program takes limit to 10.
   If there are 4 input arguments, this program recommends movies based on user traits. If you do not want to select more than one of gender, age, or occupation, please use empty double quotes. In the case of the genre, you do not need to enter empty double quotes. The input must consist of 3 or 4 arguments, including empty double quotes.
2. In case that the movie who has given title does not exist.<br/>
3. In case that the title is not inputted.<br/>
4. In case that the input arguments order is not correct.<br/>
5. In case that the age is a negative number.<br/>
6. In case that the selected genres or occupation is not on a spcified list.<br/>
7. In case that there is no movie that belongs to at least one selected genres.<br/>

---

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

---

## Authors

-김형규: developed movie-based REST controller and wrote test codes<br/> -문지헌: write README<br/> -차준형: developed user-based REST controller and refactored error handlers<br/> -홍다빈: developed movie-based recommend system algorithms.
