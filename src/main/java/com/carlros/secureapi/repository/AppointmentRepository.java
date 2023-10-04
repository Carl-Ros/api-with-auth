package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByWorkspaceId(Long workspaceId);
    Optional<Appointment> findByIdAndWorkspaceId(Long workspaceId, Long id);
}