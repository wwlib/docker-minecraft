FROM openjdk:8 as build
ENV SCRIPTCRAFT_VERSION 3.4.0
ENV AUTO_DEPLOY=${AUTO_DEPLOY}

RUN apt-get update
RUN apt-get install -y git ant maven
RUN apt-get clean

WORKDIR /opt/minecraft/

RUN git clone --depth 1 --branch 3.4.0 https://github.com/walterhiggins/ScriptCraft

WORKDIR /opt/minecraft/ScriptCraft

ADD deploy /deploy
RUN chmod +x /deploy

# CMD ["ant"]
# CMD ["bash"]
CMD ["/deploy"]
