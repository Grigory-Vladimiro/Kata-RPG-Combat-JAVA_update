package dev.grigory.rpg_update;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PropTest {

    @Test
    void propHasHealthAndCanTakeDamage() {
        Prop tree = new Prop(2000);
        tree.receiveDamage(500);
        assertEquals(1500, tree.getHealth());
    }
    @Test
    void propIsDestroyedWhenHealthReachesZero() {
        Prop rock = new Prop(1000);
        rock.receiveDamage(1000);
        assertEquals(0, rock.getHealth());
        assertTrue(rock.isDestroyed());
    }
}
