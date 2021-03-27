# Requirements
# 1. Download ubuntu 20.04.
# 2. Install all packages that are needed to run your program, such as vim, git, java 11, maven, etc.
# 3. Create /root/project folder and set it as WORKDIR.

FROM ubuntu:20.04

RUN apt-get update && apt-get install -y openssh-server
RUN apt-get -y install git gcc g++ python3 vim python3-pip
RUN pip3 install essential_generators

RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y maven

RUN mkdir /root/project

WORKDIR /root/project