package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.TimerTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerTaskRepository extends JpaRepository<TimerTask, Long> {
}
