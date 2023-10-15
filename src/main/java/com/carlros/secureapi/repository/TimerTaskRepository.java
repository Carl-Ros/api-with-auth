package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.TimerTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimerTaskRepository extends JpaRepository<TimerTask, Long> {
    List<TimerTask> findAllByWorkspaceId(Long workspaceId);
    Optional<TimerTask> findByIdAndWorkspaceId(Long id, Long workspaceId);
}
