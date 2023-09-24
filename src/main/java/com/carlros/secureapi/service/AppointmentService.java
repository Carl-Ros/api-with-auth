package com.carlros.secureapi.service;

import com.carlros.secureapi.model.Appointment;
import com.carlros.secureapi.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public List<Appointment> all() {
        return repository.findAll();
    }

    public Appointment one(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void create(Appointment appointment){
        repository.save(appointment);
    }

    public void update(Long id, Appointment appointment) {
        Appointment entry = repository.findById(id).orElseThrow();
        entry.setDateTime(appointment.getDateTime());
        repository.save(entry);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
