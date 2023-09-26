package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.ConsumableItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumableItemRepository extends JpaRepository<ConsumableItem, Long> {
}
