## mineHologram

Minecraft hologram library. Recommended use in 1.13+ version.

#### Maven

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

#### Maven relocate

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>VERSION</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
            </configuration>
        </execution>
    </executions>
    <configuration>
        <relocations>
            <relocation>
                <pattern>pl.minecodes.minehologram</pattern>
                <shadedPattern>your.package</shadedPattern>
            </relocation>
        </relocations>
    </configuration>
</plugin>
```

#### Example

```java

private final HologramAPI api = new HologramAPI();

public void onEnalbe(){
    Hologram hologram = api.createHologram(location.clone().add(0,10,0), 0.5); //location and line height

    hologram.addLine("Test line!");
    hologram.setLine(0,"New test message!");
    hologram.removeLine(0);
        
    hologram.move(newLocation);
}

public void onDisable(){
    api.unloadHolograms(); //Important option! 
}

```
