package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Entity
@Data
public class Consumable implements RemainingTimeTracker {
    private @Id @GeneratedValue Long id;
    private double quantity;
    private Instant whenMutated;
    private double dailyConsumptionRate;

    public Consumable(double quantity, double dailyConsumptionRate){
        updateWhenMutated();
        this.quantity = quantity;
        this.dailyConsumptionRate = dailyConsumptionRate;
    }

    @Override
    public long getRemainingDays() {
        long daysElapsed = Duration.between(Instant.now(), whenMutated).toDays();
        double remainingQuantity = quantity - (dailyConsumptionRate * daysElapsed);

        return (long) Math.ceil(remainingQuantity / dailyConsumptionRate);
    }

    public void setQuantity(double quantity) {
        updateWhenMutated();
        this.quantity = quantity;
    }

    public void setDailyConsumptionRate(double dailyConsumptionRate){
        updateWhenMutated();
        this.dailyConsumptionRate = dailyConsumptionRate;
    }

    private void updateWhenMutated() {
        this.whenMutated = Instant.now();
    }
}
