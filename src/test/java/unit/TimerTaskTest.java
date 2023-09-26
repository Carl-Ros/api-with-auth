package unit;

import com.carlros.secureapi.model.TimerTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimerTaskTest {

    @DisplayName("Remaining days should not be negative")
    @Test
    void getRemainingDaysNegative() {
        TimerTask timerTask = new TimerTask(-256L, "durationTest");
        assertEquals(0, timerTask.getRemainingDays());
    }

    @Test
    void setDuration() {
        TimerTask timerTask = new TimerTask(7L, "durationTest");
        assertEquals(7, timerTask.getRemainingDays());

        timerTask.setDurationInDays(14L);
        assertEquals(14, timerTask.getRemainingDays());
    }
}