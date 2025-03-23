package dev.grigory.rpg_update;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void newCharacterHasDefaultHealthLevelAndIsAlive() {
        Character character = new Character();
        assertEquals(1000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertTrue(character.isAlive());
    }
    @Test
    void characterShouldTakeDamage() {
        Character character = new Character();
        character.receiveDamage(200);
        assertEquals(800, character.getHealth());
    }
    @Test
    void characterShouldDieIfHealthReachesZero() {
        Character character = new Character();
        character.receiveDamage(1000);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }
    @Test
    void deadCharacterCannotBeHealed() {
        Character character = new Character();
        character.receiveDamage(1000); 
        character.heal(100);
        assertEquals(0, character.getHealth()); 
    }
    @Test
    void healingShouldNotExceedMaxHealth() {
        Character character = new Character();
        character.heal(100);
        assertEquals(1000, character.getHealth());

        character.receiveDamage(200);
        character.heal(300);
        assertEquals(1000, character.getHealth());
    }
    @Test
    void characterCanOnlyHealItself() {
        Character healer = new Character();
        Character target = new Character();

        target.receiveDamage(500);
        healer.heal(target, 200);
        assertEquals(500, target.getHealth());
    }
    @Test
    void damageIsReducedBy50PercentWhenTargetIsFiveOrMoreLevelsAbove() {
        Character attacker = new Character();
        Character target = new Character();

        for (int i = 0; i < 5; i++) {
        target.levelUp();
        }

        attacker.dealDamage(target, 100);
        assertEquals(950, target.getHealth());
    }
}
