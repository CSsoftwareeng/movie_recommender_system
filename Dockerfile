# Requirements
# 1. Download ubuntu 20.04.
# 2. Install all packages that are needed to run your program, such as vim, git, java 11, maven, etc.
# 3. Create /root/project folder and set it as WORKDIR.

FROM ubuntu:20.04

RUN wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add -
RUN echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-4.4.list
RUN apt-get update && apt-get install -y openssh-server curl
RUN apt-get -y install git gcc g++ python3 vim python3-pip mongodb-org
RUN pip3 install essential_generators

RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y maven

RUN mkdir /root/project

WORKDIR /root/project

ADD run.sh ./
RUN sed -i -e 's/\r$//' /root/project/run.sh
