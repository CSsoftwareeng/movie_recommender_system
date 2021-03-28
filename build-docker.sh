docker build -t cse364-ubuntu20.04/movie_recommender_os
docker run -d --name cont cse364-ubuntu20.04/movie_recommender_os 

docker cp ./run.sh ml1:/