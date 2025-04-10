package dev.grigory.rpg_update;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void newCharacterHasDefaultHealthLevelAndIsAlive() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        assertEquals(1000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertTrue(character.isAlive());
    }
    @Test
    void characterShouldTakeDamage() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        character.receiveDamage(200);
        assertEquals(800, character.getHealth());
    }
    @Test
    void characterShouldDieIfHealthReachesZero() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        character.receiveDamage(1000);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }
    @Test
    void deadCharacterCannotBeHealed() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        character.receiveDamage(1000); 
        character.heal(100);
        assertEquals(0, character.getHealth()); 
    }
    @Test
    void healingShouldNotExceedMaxHealth() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        character.heal(100);
        assertEquals(1000, character.getHealth());

        character.receiveDamage(200);
        character.heal(300);
        assertEquals(1000, character.getHealth());
    }
    @Test
    void characterCanOnlyHealItself() {
        Character healer = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        Character target = new Character(Character.CharacterType.MELEE, Faction.ORCS);

        target.receiveDamage(500);
        healer.heal(target, 200);
        assertEquals(500, target.getHealth());
    }
    @Test
    void damageIsReducedBy50PercentWhenTargetIsFiveOrMoreLevelsAbove() {
        Character attacker = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        Character target = new Character(Character.CharacterType.MELEE, Faction.HUMANS);

        for (int i = 0; i < 5; i++) {
        target.levelUp();
        }

        attacker.dealDamage(target, 100);
        assertEquals(950, target.getHealth());
    }
    @Test
    void damageIsIncreasedBy50PercentWhenTargetIsFiveOrMoreLevelsBelow() {
        Character attacker = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        Character target = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        for (int i = 0; i < 5; i++) {
        attacker.levelUp();
        }

        attacker.dealDamage(target, 100);
        assertEquals(850, target.getHealth());
    }
    @Test
    void characterCannotDealDamageToItself() {
        Character character = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        character.dealDamage(character, 100);
        assertEquals(1000, character.getHealth());
    }
    @Test
    void meleeFighterCanOnlyAttackWithinTwoMeters() {
        Character melee = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        assertTrue(melee.isInRange(2));
        assertFalse(melee.isInRange(3));
    }
    @Test
    void rangedFighterCanAttackWithinTwentyMeters() {
        Character ranged = new Character(Character.CharacterType.RANGED, Faction.HUMANS);
        assertTrue(ranged.isInRange(20));
        assertFalse(ranged.isInRange(21));
    }
    @Test
    void charactersCanOnlyDealDamageIfTargetIsInRange() {
        Character attacker = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        Character target = new Character(Character.CharacterType.MELEE, Faction.ORCS);

        attacker.dealDamage(target, 100, 3);
        assertEquals(1000, target.getHealth());

        attacker.dealDamage(target, 100, 2);
        assertEquals(900, target.getHealth());
    }
    @Test
    void alliesCannotDealDamageToEachOther() {
        Character human = new Character(Character.CharacterType.MELEE, Faction.HUMANS);
        Character elf = new Character(Character.CharacterType.MELEE, Faction.ELVES);

        human.dealDamage(elf, 100, 2);
        assertEquals(1000, elf.getHealth());
    }
    @Test
    void alliesCanHealEachOther() {
        Character elf = new Character(Character.CharacterType.RANGED, Faction.ELVES);
        Character human = new Character(Character.CharacterType.MELEE, Faction.HUMANS);

        human.receiveDamage(500);
        elf.heal(human, 200);
        assertEquals(700, human.getHealth());
    }
}
