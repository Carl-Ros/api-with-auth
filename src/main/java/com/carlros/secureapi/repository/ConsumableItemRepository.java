package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.ConsumableItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsumableItemRepository extends JpaRepository<ConsumableItem, Long> {
    List<ConsumableItem> findAllByWorkspaceId(Long workspaceId);
    Optional<ConsumableItem> findByIdAndWorkspaceId(Long id, Long workspaceId);
}
