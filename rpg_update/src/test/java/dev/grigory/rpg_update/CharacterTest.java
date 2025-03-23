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
}
