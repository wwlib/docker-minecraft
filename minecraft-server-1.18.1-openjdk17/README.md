# minecraft-server-1.18.1-openjdk11 (image)

Minecraft server version 1.18.1 works with JavaScript & ScriptCraft (partially) and is supported by Lunar Client (as of 1/1/2022)
- based on image: openjdk:17-oraclelinux8
  - Docker: FROM openjdk:17-oraclelinux8
  - Java JDK 17
- ScriptCraft v3.4.0

Note: The Nashorn JavaScript engine is NOT included in JDK 17 and must be loaded as a module:
```
java -Xms512M -Xmx1G -cp /minecraft/nashorn --module-path /minecraft/nashorn --add-modules org.openjdk.nashorn -jar spigot-$MINECRAFT_VERSION.jar
```

Then the JavaScript engine should be accesssed like:
```
ScriptEngineManager factory = new ScriptEngineManager();
this.engine = factory.getEngineByName("JavaScript");
```

However, as of Spigot/Paper 1.18.1 script engines are not found (undefined)
See: https://hub.spigotmc.org/jira/browse/SPIGOT-6902

## Nashorn

### build image
```
cd minecraft-server-1.18.1-openjdk17
docker build -f Dockerfile.openjdk17 -t wwlib/minecraft:server-openjdk17 "."
```

### run image
```
docker run --rm -p 25565:25565 -it -e AUTO_START="false" -v minecraft18:/minecraft wwlib/minecraft:server-openjdk17
```

### deploy & run
The `/deploy` script copies the server files from `/opt/minecraft` to `/minecraft` which is mounted as Docker volume `minecraft18`

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

Note: `/opt/plugins/ScriptCraft/build.xml` needs to target java 17 vs java 16

```
cd /opt/plugins/ScriptCraft
ant
cp /opt/plugins/ScriptCraft/target/scriptcraft.jar /minecraft/plugins/scriptcraft.jar
```

## GraalVM

Using container image `ghcr.io/graalvm/graalvm-ce:java17-21.3` from `https://github.com/graalvm/container/pkgs/container/graalvm-ce`

### build image
```
cd minecraft-server-1.18.1-openjdk17
docker build -f Dockerfile.openjdk17-graalvm -t wwlib/minecraft:server-openjdk17-graalvm "."
```

### run image
```
docker run --rm -p 25565:25565 -it -e AUTO_START="false" -v minecraft18gvm:/minecraft wwlib/minecraft:server-openjdk17-graalvm
```

### deploy & run
The `/deploy` script copies the server files from `/opt/minecraft` to `/minecraft` which is mounted as Docker volume `minecraft18`

```
/deploy

java -Xms512M -Xmx1G -jar spigot-$MINECRAFT_VERSION.jar
```

When running GraalVM (openjdk17) Minecraft 1.17.1 CAN access the JavaScript script engine.

1.17.1

[19:20:06] [Worker-Main-10/INFO]: Preparing spawn area: 91%
[19:20:06] [Server thread/INFO]: Time elapsed: 3874 ms
[19:20:06] [Server thread/INFO]: [hellojavascript] Enabling hellojavascript v0.1
[19:20:06] [Server thread/ERROR]: [hellojavascript] HelloJavaScript plugin enabled.
[19:20:06] [Server thread/ERROR]: [hellojavascript] VM Details:
[19:20:06] [Server thread/ERROR]: [hellojavascript] OpenJDK 64-Bit Server VM
[19:20:06] [Server thread/ERROR]: [hellojavascript] 17.0.1+12-jvmci-21.3-b05
[19:20:06] [Server thread/ERROR]: [hellojavascript] mixed mode, sharing
[19:20:07] [Server thread/ERROR]: [hellojavascript] Found a JavaScript engine.
[19:20:07] [Server thread/INFO]: Server permissions file permissions.yml is empty, ignoring it
[19:20:07] [Server thread/INFO]: Done (35.591s)! For help, type "help"

When running GraalVM (openjdk17) Minecraft 1.18.1 CANNOT access the JavaScript script engine.

1.18.1

[19:29:50] [Worker-Main-8/INFO]: Preparing spawn area: 97%
[19:29:51] [Server thread/INFO]: Time elapsed: 7584 ms
[19:29:51] [Server thread/INFO]: [hellojavascript] Enabling hellojavascript v0.1
[19:29:51] [Server thread/ERROR]: [hellojavascript] HelloJavaScript plugin enabled.
[19:29:51] [Server thread/ERROR]: [hellojavascript] VM Details:
[19:29:51] [Server thread/ERROR]: [hellojavascript] OpenJDK 64-Bit Server VM
[19:29:51] [Server thread/ERROR]: [hellojavascript] 17.0.1+12-jvmci-21.3-b05
[19:29:51] [Server thread/ERROR]: [hellojavascript] mixed mode, sharing
[19:29:51] [Server thread/ERROR]: [hellojavascript] No JavaScript Engine available.
[19:29:51] [Server thread/ERROR]: [hellojavascript] Available engines include:
[19:29:51] [Server thread/INFO]: Server permissions file permissions.yml is empty, ignoring it
[19:29:51] [Server thread/INFO]: Done (103.668s)! For help, type "help"

See: https://hub.spigotmc.org/jira/browse/SPIGOT-6902