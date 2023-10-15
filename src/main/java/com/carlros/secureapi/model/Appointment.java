package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Appointment extends Todo implements RemainingTimeTracker {
    private @NotNull LocalDateTime dateTime;

    @Override
    public long getRemainingDays() {
        long daysRemaining = Duration.between(LocalDateTime.now(), dateTime).toDays();
        return daysRemaining > 0 ? daysRemaining : 0;
    }
}
