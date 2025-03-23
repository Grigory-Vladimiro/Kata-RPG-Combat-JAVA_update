package dev.grigory.rpg_update;

public enum Faction {
    HUMANS, ELVES, ORCS, TROLLS;

    public boolean isAlly(Faction other) {
        return (this == HUMANS || this == ELVES) && (other == HUMANS || other == ELVES)
            || (this == ORCS || this == TROLLS) && (other == ORCS || other == TROLLS);
    }
}
