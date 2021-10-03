#mineHologram
Minecraft hologram library.


```java

private final HologramAPI api = new HologramAPI();

public void onEnalbe(){     
    api.createHologram(location); //location, line height is defalt 0.25
    api.createHologram(location.clone().add(0, 10, 0), 0.5); //location and line height
}

public void onDisable() {
    api.unloadHolograms(); //Important option!
}

```