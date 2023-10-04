package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository repository;

    public Workspace one(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Workspace with id %s does not exist.", id)));
    }

    public List<Workspace> all() {
        return repository.findAll();
    }

    public void create(Workspace workspace) {
        repository.save(workspace);
    }

    public void update(Long id, Workspace workspace) {
        Workspace entry = one(id);

        if(workspace.getName() != null) {
            entry.setName(workspace.getName());
        }

        repository.save(entry);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
