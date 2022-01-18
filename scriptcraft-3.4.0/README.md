# scriptcraft-3.4.0

ScriptCraft version 3.4.0 is the most recent ScriptCraft release and works with Minecraft server 1.12.2 which uses Java 8 (1.8)
- based on image: openjdk:8
  - Java JDK 1.8

### build image
```
cd scriptcraft-3.4.0
docker build -f Dockerfile -t wwlib/docker-minecraft:scriptcraft-plugin "."

```

### run image
Note: Using Docker volume `minecraft12` - same as `minecraft-server-1.12.2` container

```
docker run --rm -it -e AUTO_DEPLOY="true" -v minecraft12:/minecraft wwlib/docker-minecraft:scriptcraft-plugin
```

### deploy scriptcraft.jar
```
/deploy
```

Or

```
cd /opt/minecraft/ScriptCraft
ant
cp /opt/minecraft/ScriptCraft/target/scriptcraft.jar /minecraft/plugins/scriptcraft.jar
```