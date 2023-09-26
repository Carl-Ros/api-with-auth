package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@NoArgsConstructor
public class TimerTask implements RemainingTimeTracker {
    private @Id @GeneratedValue Long id;
    private @NotNull @Min(0) Long durationInDays;
    private Instant whenMutated;
    private @NotNull @NotEmpty String name;

    public TimerTask(Long durationInDays, String name){
        updateWhenMutated();
        this.durationInDays = durationInDays;
        this.name = name;
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
