package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data()
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class TimerTask extends Todo implements RemainingTimeTracker {
    private @NotNull @Min(0) Long durationInDays;
    private Instant whenMutated;

    public TimerTask(Long durationInDays, String name){
        setName(name);
        updateWhenMutated();
        this.durationInDays = durationInDays;
    }

    @Override
    public long getRemainingDays() {
        long daysRemaining = Duration.of(durationInDays, ChronoUnit.DAYS).minus(Duration.between(Instant.now(), whenMutated)).toDays();
        return daysRemaining > 0 ? daysRemaining : 0;
    }

    public void setDurationInDays(Long duration){
        if(!duration.equals(this.durationInDays)) {
            this.durationInDays = duration;
            updateWhenMutated();
        }
    }

    private void setWhenMutated(Instant whenMutated){
        this.whenMutated = whenMutated;
    }

    private Instant getWhenMutated() {
        return this.whenMutated;
    }

    private void updateWhenMutated() {
        this.whenMutated = Instant.now();
    }
}
