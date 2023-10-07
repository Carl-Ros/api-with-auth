package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.Appointment;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public List<Appointment> all(Long workspaceId) {
        return repository.findAllByWorkspaceId(workspaceId);
    }

    public Appointment one(Long workspaceId, Long id) {
        return repository.findByIdAndWorkspaceId(id, workspaceId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Appointment with id %s does not exist.", id))
        );
    }

    public void create(Workspace workspace, Appointment appointment) {
        appointment.setWorkspace(workspace);
        repository.save(appointment);
    }

    public void update(Long workspaceId, Long id, Appointment appointment) {

        Appointment entry = one(workspaceId, id);

        if(appointment.getDateTime() != null) {
            entry.setDateTime(appointment.getDateTime());
        }

        entry.updateInheritedAttributes(appointment);

        repository.save(entry);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll(Long workspaceId){
        all(workspaceId).forEach((entry) -> delete(entry.getId()));
    }
}
