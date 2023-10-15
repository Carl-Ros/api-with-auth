package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.TimerTask;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.repository.TimerTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerTaskService {
    private final TimerTaskRepository repository;

    public List<TimerTask> all(Long workspaceId) {
        return repository.findAllByWorkspaceId(workspaceId);
    }

    public TimerTask one(Long workspaceId, Long id) {
        return repository.findByIdAndWorkspaceId(id, workspaceId).orElseThrow(
                () -> new EntityNotFoundException(String.format("TimerTask with id %s does not exist.", id))
        );
    }

    public TimerTask create(Workspace workspace, TimerTask timerTask) {
        timerTask.setWorkspace(workspace);
        return repository.save(timerTask);
    }
    public void update(Long workspaceId, Long id, TimerTask timerTask){
        TimerTask entry = one(workspaceId, id);

        if(timerTask.getDurationInDays() != null) {
            entry.setDurationInDays(timerTask.getDurationInDays());
        }

        if(timerTask.getName() != null) {
            entry.setName(timerTask.getName());
        }

        entry.updateInheritedAttributes(timerTask);

        repository.save(entry);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
