package dev.grigory.rpg_update;

public class Character {
    private int health = 1000;
    private int level = 1;
    private boolean alive = true;
    private final Faction faction;

    public Character(CharacterType type, Faction faction) {
        this.type = type;
        this.faction = faction; 
    }
    public boolean isAlly(Character other) {
        return this.faction.isAlly(other.faction);
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAlive() {
        return alive;
    }
    public void receiveDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }
    public void heal(int amount) {
        if (!alive) return;
        health = Math.min(health + amount, 1000);
    }
    public void heal(Character target, int amount) {
        if (this != target) return;
        target.heal(amount);
    }
    public void levelUp() {
        level++;
    }
    public void dealDamage(Character target, int damage) {
        if (target == this) return;    
        int adjustedDamage = damage;
        if (target.level >= this.level + 5) {
            adjustedDamage = damage / 2;
        } else if (this.level >= target.level + 5) {
            adjustedDamage = (int) (damage * 1.5);
        }
        target.receiveDamage(adjustedDamage);
    }
    public enum CharacterType {
        MELEE(2),
        RANGED(20);
    
        private final int attackRange;
    
        CharacterType(int attackRange) {
            this.attackRange = attackRange;
        }
    
        public int getAttackRange() {
            return attackRange;
        }
    }
    private final CharacterType type;
    public boolean isInRange(int distance) {
        return distance <= type.getAttackRange();
    }
    public void dealDamage(Character target, int damage, int distance) {
        if (target == this) return;
        if (!isInRange(distance)) return;
        if (isAlly(target)) return;
        int adjustedDamage = damage;
        if (target.level >= this.level + 5) {
            adjustedDamage = damage / 2;
        } else if (this.level >= target.level + 5) {
            adjustedDamage = (int) (damage * 1.5);
        }
        target.receiveDamage(adjustedDamage);
    }
}
