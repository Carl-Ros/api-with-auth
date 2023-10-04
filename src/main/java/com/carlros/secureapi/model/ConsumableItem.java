package com.carlros.secureapi.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ConsumableItem extends Todo implements RemainingTimeTracker {
    private @NotNull @Min(0) Double quantity;
    private Instant whenMutated;
    private @NotNull @Min(0) Double dailyConsumptionRate;

    public ConsumableItem(Double quantity, Double dailyConsumptionRate, String name){
        updateWhenMutated();
        this.quantity = quantity;
        this.dailyConsumptionRate = dailyConsumptionRate;
        setName(name);
    }

    // Weekends are not counted since tracking is tailored towards pre-school items, which are not used during the weekend.
    @Override
    public long getRemainingDays() {
        long daysElapsed = 0;

        for(LocalDate date = LocalDate.from(whenMutated.atZone(ZoneId.systemDefault())); date.isBefore(LocalDate.now());  date = date.plusDays(1)){
            if(!(date.getDayOfWeek() == DayOfWeek.SATURDAY) && !(date.getDayOfWeek() == DayOfWeek.SUNDAY)){
                daysElapsed++;
            }
        }

        double remainingQuantity = quantity - (dailyConsumptionRate * daysElapsed);
        long approxDaysRemaining = (long) Math.ceil(remainingQuantity / dailyConsumptionRate);

        return approxDaysRemaining > 0 ? approxDaysRemaining : 0;
    }

    public void setQuantity(Double quantity) {
        if(!quantity.equals(this.quantity)) {
            updateWhenMutated();
            this.quantity = quantity;
        }
    }

    public void setDailyConsumptionRate(Double dailyConsumptionRate){
        if(!dailyConsumptionRate.equals(this.dailyConsumptionRate)){
            updateWhenMutated();
            this.dailyConsumptionRate = dailyConsumptionRate;
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
