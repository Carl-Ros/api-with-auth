package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.ConsumableItem;
import com.carlros.secureapi.repository.ConsumableItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumableItemService {
    private final ConsumableItemRepository repository;

    public ConsumableItem one(Long id){
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Consumable with id %s does not exist.", id))
        );
    }

    public List<ConsumableItem> all(){
        return repository.findAll();
    }

    public void create(ConsumableItem consumableItem){
        repository.save(consumableItem);
    }

    public void update(Long id, ConsumableItem consumableItem){
        ConsumableItem entry = one(id);

        if(consumableItem.getQuantity() != null) {
            entry.setQuantity(consumableItem.getQuantity());
        }

        if(consumableItem.getDailyConsumptionRate() != null) {
            entry.setDailyConsumptionRate(consumableItem.getDailyConsumptionRate());
        }

        if(consumableItem.getName() != null) {
            entry.setName(consumableItem.getName());
        }

        repository.save(entry);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
