package pl.minecodes.minehologram.hologram.element;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import pl.minecodes.minehologram.color.ColorUtil;

public class HologramLine {

    private int index;
    private String text;
    private Location location;
    private final ArmorStand armorStand;

    public HologramLine(int index, String text, Location location, ArmorStand armorStand) {
        this.index = index;
        this.text = text;
        this.location = location;
        this.armorStand = armorStand;
    }

    public void prepareLine() {
        if (this.text.isEmpty()) {
            armorStand.setCustomNameVisible(false);
            return;
        }
        armorStand.setCustomName(ColorUtil.implementColors(text));
    }

    public void updateLine(String text) {
        this.text = text;
        this.armorStand.setCustomName(ColorUtil.implementColors(text));
    }

    public void updateIndex(int index) {
        this.index = index;
    }

    public void updateLocation(Location location) {
        this.location = location;
        this.armorStand.teleport(this.location);
    }

    public void removeLine() {
        this.armorStand.remove();
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public Location getLocation() {
        return location;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
