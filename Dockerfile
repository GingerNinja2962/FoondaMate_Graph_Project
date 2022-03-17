FROM ubuntu:20.04 as base

ENV ZA=Africa/Johannesburg
RUN ln -snf /usr/share/zoneinfo/$ZA /etc/localtime && \
    echo $ZA > /etc/timezone

# Prerequisites
RUN apt update && \
    apt install -y apt-utils openjdk-17-jdk wget

# Install Maven
RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -P /tmp && \
    tar xvzf /tmp/apache-maven-*.tar.gz -C /tmp &&  \
    mkdir -p /opt/maven &&  \
    mv /tmp/apache-maven-3.8.5/* /opt/maven
ENV PATH="${PATH:+${PATH}:}/opt/maven/bin"

# Copy over project files
RUN mkdir ./FoondaMate_CLI_Graph_project
COPY ./ ./FoondaMate_CLI_Graph_project

# Run all Flutter Tests
RUN cd ./FoondaMate_CLI_Graph_project && \
    mvn test
