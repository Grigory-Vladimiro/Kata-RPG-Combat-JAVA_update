package dev.grigory.rpg_update;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void newCharacterHasDefaultHealthLevelAndIsAlive() {
        Character character = new Character(Character.CharacterType.MELEE);;
        assertEquals(1000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertTrue(character.isAlive());
    }
    @Test
    void characterShouldTakeDamage() {
        Character character = new Character(Character.CharacterType.MELEE);;
        character.receiveDamage(200);
        assertEquals(800, character.getHealth());
    }
    @Test
    void characterShouldDieIfHealthReachesZero() {
        Character character = new Character(Character.CharacterType.MELEE);;
        character.receiveDamage(1000);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }
    @Test
    void deadCharacterCannotBeHealed() {
        Character character = new Character(Character.CharacterType.MELEE);;
        character.receiveDamage(1000); 
        character.heal(100);
        assertEquals(0, character.getHealth()); 
    }
    @Test
    void healingShouldNotExceedMaxHealth() {
        Character character = new Character(Character.CharacterType.MELEE);;
        character.heal(100);
        assertEquals(1000, character.getHealth());

        character.receiveDamage(200);
        character.heal(300);
        assertEquals(1000, character.getHealth());
    }
    @Test
    void characterCanOnlyHealItself() {
        Character healer = new Character(Character.CharacterType.MELEE);;
        Character target = new Character(Character.CharacterType.MELEE);;

        target.receiveDamage(500);
        healer.heal(target, 200);
        assertEquals(500, target.getHealth());
    }
    @Test
    void damageIsReducedBy50PercentWhenTargetIsFiveOrMoreLevelsAbove() {
        Character attacker = new Character(Character.CharacterType.MELEE);;
        Character target = new Character(Character.CharacterType.MELEE);;

        for (int i = 0; i < 5; i++) {
        target.levelUp();
        }

        attacker.dealDamage(target, 100);
        assertEquals(950, target.getHealth());
    }
    @Test
    void damageIsIncreasedBy50PercentWhenTargetIsFiveOrMoreLevelsBelow() {
        Character attacker = new Character(Character.CharacterType.MELEE);;
        Character target = new Character(Character.CharacterType.MELEE);;
        for (int i = 0; i < 5; i++) {
        attacker.levelUp();
        }

        attacker.dealDamage(target, 100);
        assertEquals(850, target.getHealth());
    }
    @Test
    void characterCannotDealDamageToItself() {
        Character character = new Character(Character.CharacterType.MELEE);;
        character.dealDamage(character, 100);
        assertEquals(1000, character.getHealth());
    }
    @Test
    void meleeFighterCanOnlyAttackWithinTwoMeters() {
        Character melee = new Character(Character.CharacterType.MELEE);
        assertTrue(melee.isInRange(2));
        assertFalse(melee.isInRange(3));
    }
    @Test
    void rangedFighterCanAttackWithinTwentyMeters() {
        Character ranged = new Character(Character.CharacterType.RANGED);
        assertTrue(ranged.isInRange(20));
        assertFalse(ranged.isInRange(21));
    }
    @Test
    void charactersCanOnlyDealDamageIfTargetIsInRange() {
        Character attacker = new Character(Character.CharacterType.MELEE);
        Character target = new Character(Character.CharacterType.MELEE);

        attacker.dealDamage(target, 100, 3);
        assertEquals(1000, target.getHealth());

        attacker.dealDamage(target, 100, 2);
        assertEquals(900, target.getHealth());
    }
}
