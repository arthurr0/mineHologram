package pl.minecodes.minehologram.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import pl.minecodes.minehologram.hologram.element.HologramLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Hologram {

    private final List<HologramLine> lines = new ArrayList<>();

    private Location mainLocation;
    private final double lineHeight;

    public Hologram(Location mainLocation, double lineHeight) {
        this.mainLocation = mainLocation;
        this.lineHeight = lineHeight;
    }

    public Hologram(Location mainLocation) {
        this.mainLocation = mainLocation;
        this.lineHeight = 0.25;
    }

    public void addLine(String message) {
        ArmorStand armorStand = this.lines.isEmpty() ?
                this.spawnEntity(this.mainLocation) :
                this.spawnEntity(this.highestLineLocation().clone().clone().add(0, -this.lineHeight, 0));
        this.configureArmorStand(armorStand);

        HologramLine line = HologramLine.builder()
                .index(this.highestIndex() + 1)
                .armorStand(armorStand)
                .location(armorStand.getLocation())
                .text(message)
                .build();
        line.prepareLine();
        this.lines.listIterator().add(line);
    }

    public void setLine(int index, String text) {
        HologramLine line = this.getLine(index);
        if (line == null) return;
        line.updateLine(text);
    }

    public void removeLine(int index) {
        HologramLine line = this.getLine(index);
        if (line == null) return;
        line.removeLine();
        lines.remove(line);

        this.lines.sort(Comparator.comparing(HologramLine::getIndex));

        int newIndex = 1;
        Location lastLocation = this.mainLocation.clone().add(0, this.lineHeight, 0);
        this.lines.forEach(loopLine -> {
            loopLine.updateIndex(newIndex);
            loopLine.updateLocation(lastLocation.add(0, -this.lineHeight, 0));
        });
    }

    public void move(Location location) {
        this.mainLocation = location;
        this.lines.sort(Comparator.comparing(HologramLine::getIndex));
        Location lastLocation = this.mainLocation.clone().add(0, this.lineHeight, 0);
        this.lines.forEach(loopLine -> loopLine.updateLocation(lastLocation.add(0, -this.lineHeight, 0)));
    }

    public void clearLines() {
        this.lines.forEach(HologramLine::removeLine);
    }

    private ArmorStand spawnEntity(Location location) {
        return (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
    }

    private HologramLine getLine(int index) {
        return this.lines.stream().filter(line -> line.getIndex() == index).findFirst().orElse(null);
    }

    private void configureArmorStand(ArmorStand armorStand) {
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        armorStand.setCanPickupItems(false);
        armorStand.setMarker(true);
    }

    private Location highestLineLocation() {
        return this.getLine(this.highestIndex()).getLocation();
    }

    private int highestIndex() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.lines.forEach(line -> {
            if (line.getIndex() > atomicInteger.get()) {
                atomicInteger.set(line.getIndex());
            }
        });
        return atomicInteger.get();
    }

}
