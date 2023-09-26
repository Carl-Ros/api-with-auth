package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Appointment implements RemainingTimeTracker {
    private @Id @GeneratedValue Long id;
    private @NotNull LocalDateTime dateTime;
    private @NotNull @NotEmpty String name;

    @Override
    public long getRemainingDays() {
        long daysRemaining = Duration.between(LocalDateTime.now(), dateTime).toDays();
        return daysRemaining > 0 ? daysRemaining : 0;
    }
}
