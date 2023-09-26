package unit;

import com.carlros.secureapi.model.ConsumableItem;
import com.carlros.secureapi.repository.ConsumableItemRepository;
import com.carlros.secureapi.service.ConsumableItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsumableItemServiceTest {
    @Mock
    private ConsumableItemRepository repository;

    @InjectMocks
    private ConsumableItemService service;

    @DisplayName("Update consumables")
    @Test
    void update() {
        Long id = 1L;
        ConsumableItem consumableItem = new ConsumableItem();
        consumableItem.setId(id);
        consumableItem.setQuantity(16.5);
        consumableItem.setDailyConsumptionRate(4d);

        when(repository.findById(consumableItem.getId())).thenReturn(Optional.of(consumableItem));

        ConsumableItem updateConsumableItem = new ConsumableItem();

        updateConsumableItem.setQuantity(33d);
        service.update(id, updateConsumableItem);

        updateConsumableItem.setDailyConsumptionRate(8d);
        service.update(id, updateConsumableItem);

        verify(repository, times(2)).findById(id);
        verify(repository, times(2)).save(consumableItem);

        assertEquals(33d, consumableItem.getQuantity());
        assertEquals(8d, consumableItem.getDailyConsumptionRate());
    }
}