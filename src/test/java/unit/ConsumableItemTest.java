package unit;

import com.carlros.secureapi.model.ConsumableItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsumableItemTest {

    @Test
    void setQuantity() {
        ConsumableItem consumableItem = new ConsumableItem(16d, 4d, "testConsumable");
        assertEquals(4d, consumableItem.getRemainingDays());

        consumableItem.setQuantity(8d);
        assertEquals(2d, consumableItem.getRemainingDays());
    }

    @Test
    void setDailyConsumptionRate() {
        ConsumableItem consumableItem = new ConsumableItem(16d, 4d, "testConsumable");
        assertEquals(4d, consumableItem.getRemainingDays());

        consumableItem.setDailyConsumptionRate(8d);
        assertEquals(2d, consumableItem.getRemainingDays());
    }

    @DisplayName("Remaining days should ignore the weekend")
    @Test
    void getRemainingDaysWeekend() throws NoSuchFieldException, IllegalAccessException {

        ConsumableItem consumableItem = new ConsumableItem(7d, 1d, "testConsumable");

        Field field = ConsumableItem.class.getDeclaredField("whenMutated");
        field.setAccessible(true);
        field.set(consumableItem, Instant.now().minus(7, ChronoUnit.DAYS));

        long daysRemaining = consumableItem.getRemainingDays();

        // maximum of three days should be ignored, if starting on weekend
        assertTrue(daysRemaining == 2d || daysRemaining == 3d);
    }

    @DisplayName("Remaining days should not be negative")
    @Test
    void getRemainingDaysNegative() {
        ConsumableItem consumableItem = new ConsumableItem(-1d, 2d, "testConsumable");
        assertEquals(0, consumableItem.getRemainingDays());
    }
}