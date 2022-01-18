# minecraft-server-1.12.2

Minecraft server version 1.12.2 works with JavaScript & ScriptCraft and is supported by Lunar Client (as of 1/1/2022)
- based on image: openjdk:8
  - Java JDK 1.8
- ScriptCraft v3.4.0

Note: The Nashorn JavaScript engine is included in JDK 1.8 and can be referenced from Java like:
```
ScriptEngineManager factory = new ScriptEngineManager();
this.engine = factory.getEngineByName("JavaScript");
```

### build image
```
cd minecraft-server-1.12.2
docker build -f Dockerfile -t wwlib/minecraft:server-1.12.2 "."
```

### run image
```
docker run --rm -p 25565:25565 -it -e AUTO_START="true" -v minecraft12:/minecraft wwlib/minecraft:server-1.12.2
```

### deploy & run
The `/deploy` script copies the server files from `/opt/minecraft` to `/minecraft` which is mounted as Docker volume `minecraft12`

```
/deploy

java -Xms512M -Xmx1G -jar spigot-$MINECRAFT_VERSION.jar
```
