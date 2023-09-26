package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.TimerTask;
import com.carlros.secureapi.repository.TimerTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerTaskService {
    private final TimerTaskRepository repository;

    public TimerTask one(Long id){
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Timer with id %s does not exist.", id))
        );
    }

    public List<TimerTask> all(){
        return repository.findAll();
    }

    public void create(TimerTask timerTask){
        repository.save(timerTask);
    }

    public void update(Long id, TimerTask timerTask){
        TimerTask entry = one(id);

        if(timerTask.getDurationInDays() != null) {
            entry.setDurationInDays(timerTask.getDurationInDays());
        }

        if(timerTask.getName() != null) {
            entry.setName(timerTask.getName());
        }

        repository.save(entry);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
