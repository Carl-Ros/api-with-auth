package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Data
@Entity
public class TimerTask implements RemainingTimeTracker {
    private @Id @GeneratedValue Long id;
    private Duration duration;
    private Instant whenMutated;
    private boolean repeat;

    public TimerTask(Duration duration, boolean repeat){
        updateWhenMutated();
        this.duration = duration;
        this.repeat = repeat;
    }

    @Override
    public long getRemainingDays() {
        long daysRemaining = duration.minus(Duration.between(Instant.now(), whenMutated)).toDays();
        return daysRemaining > 0 ? daysRemaining : 0;
    }

    public void setDuration(Duration duration){
        this.duration = duration;
        updateWhenMutated();
    }

    private void updateWhenMutated() {
        this.whenMutated = Instant.now();
    }
}
