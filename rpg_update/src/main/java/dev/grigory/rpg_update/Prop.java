package dev.grigory.rpg_update;

public class Prop {
    private int health;

    public Prop(int health) {
        this.health = health;
    }

    public void receiveDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public int getHealth() {
        return health;
    }
}
