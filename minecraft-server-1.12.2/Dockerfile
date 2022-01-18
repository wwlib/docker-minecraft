FROM openjdk:8 as build
# FROM ghcr.io/graalvm/graalvm-ce:java8-20.3.3
ENV MINECRAFT_VERSION=1.12.2

# debian linux
RUN apt-get update
RUN apt-get install -y git
RUN apt-get clean

# oracle linux
# RUN microdnf update && microdnf install git

# Spigot (Minecraft server)
ADD https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar /opt/minecraft/BuildTools.jar
WORKDIR /opt/minecraft/

RUN java -Xmx1024M -jar BuildTools.jar --rev $MINECRAFT_VERSION


FROM openjdk:8
# FROM ghcr.io/graalvm/graalvm-ce:java8-20.3.3
ENV MINECRAFT_VERSION=1.12.2
ENV AUTO_START = ${AUTO_START}

# debian linux
RUN apt-get update
RUN apt-get install -y nano
RUN apt-get clean

WORKDIR /opt/minecraft/
COPY --from=build /opt/minecraft /opt/minecraft
ADD eula.txt /opt/minecraft/eula.txt
ADD server.properties /opt/minecraft/server.properties
ADD ops.json /opt/minecraft/ops.json

ADD deploy /deploy
RUN chmod +x /deploy

ADD start /start
RUN chmod +x /start

EXPOSE 25565 22

#CMD ["bash"]
CMD ["/start"]