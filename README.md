# movie_recommender_system
For this milestone, we build the code reading arguments, parsing movie dataset by genre, parsing movie dataset by occupation, and calculating average rating of occupation and genres.

## how to run
This code runs
> java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass [genres] [occupation]

>   > Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Comedy educator

or

>   > Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Comedy|Action doctor

* [genres]
    - Input the wanted movie genres
    - By using "|", multiple genres can be inputted.
    - Output a rating average of all movies in the inputted genres.
    - If genres are not inputted, this program ends.

* [occupation]
    - Input the occupation.
    - Only one occupation can be inputted.
    - If no occupation is inputted or many occupations are inputted, this program ends.

For user convenience, all of the following can be used as input:

1. The case that the spelling is correct regardless of case
> Ex. AdMiN => admin
2. The case that the spelling is correct even if there is a spacing error
> Ex. a d m in => admin
3. The case that '-' is omitted or replaced by spacing
> Ex. self employed => self-employed

The program terminates with the phrase "Program was forced to exit." in the following cases:

1. The cases that there is invalid input
    - The case that there are not two Argument
    - The case that it does not correspond to the specified input list
    - The case that it is inputted like [occupation] [genres] 
2. The case that there is no movie that contains all inputted genres
3. The case that there is no user that contains inputted occupation.

If it is a valid input, this program output as follows:
> Sum:  Count:  Average: 

>   > Ex. java -cp target/cse364-project-1-1.0-SNAPSHOT-jar-with-dependencies.jar com.recommend.app.MainClass Adventure educator
>   > Sum: 34979 Count: 10215 Average: 3.424278

If there is no user who have occupation and doesn't watch movie that contains all inputted genres, this program output as follows:
>> Sum: 0 Count: 0 Average: 0

## Authors

김형규 creates the code for Arguments and Rating Calculator.

홍다빈 creates the code for parsing Movielist.

차준형 creates the code for parsing Userlist.

문지헌 creates the code for MainClass and writes README.
