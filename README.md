# docker-minecraft

Docker images that are useful for running and testing minecraft projects - especially those that use the Nashorn JavaScript engine.

See the README files in:
- minecraft-server-1.12.2
  - JavaScript works
- minecraft-server-1.17.1-openjdk16
  - JavaScript works
- minecraft-server-1.18.1-openjdk17
  - JavaScript does NOT work - spigot-1.18.1 loads plugins differenly and Nashorn is not available
  - working on a fix/workaround
  - see: https://hub.spigotmc.org/jira/browse/SPIGOT-6902
- scriptcraft-3.4.0
  - builds successfuly for 1.12.2, 1.17.1, 1.18.1
