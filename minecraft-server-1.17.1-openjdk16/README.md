# minecraft-server-1.17.1-openjdk11 (image)

Minecraft server version 1.17.1 works with JavaScript & ScriptCraft (partially) and is supported by Lunar Client (as of 1/1/2022)
- based on image: openjdk:16-oraclelinux8
  - Docker: FROM openjdk:16-oraclelinux8
  - Java JDK 16
- ScriptCraft v3.4.0

Note: The Nashorn JavaScript engine is NOT included in JDK 16 and must be loaded as a module:
```
java -Xms512M -Xmx1G -cp /minecraft/nashorn --module-path /minecraft/nashorn --add-modules org.openjdk.nashorn -jar spigot-$MINECRAFT_VERSION.jar
```

Then the JavaScript engine CAN be accesssed like:
```
ScriptEngineManager factory = new ScriptEngineManager();
this.engine = factory.getEngineByName("JavaScript");
```

### build image
```
cd minecraft-server-1.17.1-openjdk16
docker build -f Dockerfile.openjdk16 -t wwlib/minecraft:server-openjdk16 "."

```

### run image
```
docker run --rm -p 25565:25565 -it -e AUTO_START="false" -v minecraft17:/minecraft wwlib/minecraft:server-openjdk16
```

### deploy & run
The `/deploy` script copies the server files from `/opt/minecraft` to `/minecraft` which is mounted as Docker volume `minecraft17`

The `/deploy` script also copies the Nashorn jars from `/opt/nashorn` to `/minecraft/nashorn`

```
/deploy

java -Xms512M -Xmx1G -cp /minecraft/nashorn --module-path /minecraft/nashorn --add-modules org.openjdk.nashorn -jar spigot-$MINECRAFT_VERSION.jar
```

### plugin-javascript
A plugin for testing the Nashorn JavaScript engine.

Build and deploy the `hellojavascript.jar` plugin to `/minecraft/plugins`

```
cd /opt/plugins/plugin-javascript
./build
```

### scriptcraft plugin
A plugin for authoring Spigot plugin modules in JavaScript (by Walter Higgins)
- https://github.com/walterhiggins/ScriptCraft
- (fork used in this example) https://github.com/wwlib/ScriptCraft/tree/nashorn15.3

Note: `/opt/plugins/ScriptCraft/build.xml` needs to target java 16

```
cd /opt/plugins/ScriptCraft
ant
cp /opt/plugins/ScriptCraft/target/scriptcraft.jar /minecraft/plugins/scriptcraft.jar
```
