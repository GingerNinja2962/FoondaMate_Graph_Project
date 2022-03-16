FROM ubuntu:20.04 as base

ENV ZA=Africa/Johannesburg
RUN ln -snf /usr/share/zoneinfo/$ZA /etc/localtime && echo $ZA > /etc/timezone

# Prerequisites
RUN apt update && apt install -y apt-utils openjdk-17-jdk maven libsisu-guice-java

# Copy over project files
RUN mkdir ./FoondaMate_CLI_Graph_project
COPY ./ ./FoondaMate_CLI_Graph_project

# Run all Flutter Tests
RUN ls -la
RUN cd ./FoondaMate_CLI_Graph_project && mvn test
