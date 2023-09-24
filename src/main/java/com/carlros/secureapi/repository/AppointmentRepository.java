package com.carlros.secureapi.repository;

import com.carlros.secureapi.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}