package pl.minecodes.minehologram;

import org.bukkit.Location;
import pl.minecodes.minehologram.hologram.Hologram;

import java.util.ArrayList;
import java.util.List;

public class HologramAPI {

    private final List<Hologram> holograms = new ArrayList<>();

    public Hologram createHologram(Location location) {
        Hologram hologram = new Hologram(location);
        this.holograms.listIterator().add(hologram);
        return hologram;
    }

    public Hologram createHologram(Location location, double lineHeight) {
        Hologram hologram = new Hologram(location, lineHeight);
        this.holograms.listIterator().add(hologram);
        return hologram;
    }

    public void unloadHolograms() {
        this.holograms.forEach(Hologram::clearLines);
        this.holograms.clear();
    }

    public void deleteHologram(Hologram hologram) {
        hologram.clearLines();
        this.holograms.remove(hologram);
    }
}
