package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.ConsumableItem;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.repository.ConsumableItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumableItemService {
    private final ConsumableItemRepository repository;

    public List<ConsumableItem> all(Long workspaceId) {
        return repository.findAllByWorkspaceId(workspaceId);
    }

    public ConsumableItem one(Long workspaceId, Long id) {
        return repository.findByIdAndWorkspaceId(id, workspaceId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Consumable with id %s does not exist.", id))
        );
    }

    public void create(Workspace workspace, ConsumableItem consumable) {
        consumable.setWorkspace(workspace);
        repository.save(consumable);
    }
    
    public void update(Long workspaceId, Long id, ConsumableItem consumableItem){
        ConsumableItem entry = one(workspaceId, id);

        if(consumableItem.getQuantity() != null) {
            entry.setQuantity(consumableItem.getQuantity());
        }

        if(consumableItem.getDailyConsumptionRate() != null) {
            entry.setDailyConsumptionRate(consumableItem.getDailyConsumptionRate());
        }

        if(consumableItem.getName() != null) {
            entry.setName(consumableItem.getName());
        }

        entry.updateInheritedAttributes(consumableItem);

        repository.save(entry);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
