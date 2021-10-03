<h3>mineHologram</h3>
Minecraft hologram library.
<br>
<br>

```xml
<repository>
    <id>mineCodes-repo</id>
    <url>https://repo.minecodes.pl/</url>
</repository>

<dependency>
    <groupId>pl.minecodes</groupId>
    <artifactId>mineHologram</artifactId>
    <version>1.0.4</version>
</dependency>
```

<br>
<br>

```java

private final HologramAPI api = new HologramAPI();

public void onEnalbe(){     
    Hologram hologram = api.createHologram(location.clone().add(0, 10, 0), 0.5); //location and line height
 
    hologram.addLine("Test line!");
    hologram.setLine(0, "New test message!");
    hologram.removeLine(0);
}

public void onDisable() {
    api.unloadHolograms(); //Important option!
}

```