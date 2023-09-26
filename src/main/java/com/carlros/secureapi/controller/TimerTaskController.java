package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.TimerTask;
import com.carlros.secureapi.service.TimerTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TimerTaskController {
    private final TimerTaskService service;

    @GetMapping("/timers")
    public List<TimerTask> readAll(){
        return service.all();
    }

    @GetMapping("/timers/{id}")
    public TimerTask readOne(@PathVariable  Long id){
        return service.one(id);
    }

    @PostMapping("/timers")
    public void create(@RequestBody TimerTask timerTask){
        service.create(timerTask);
    }

    @PatchMapping("/timers/{id}")
    public void update(@PathVariable Long id, @RequestBody TimerTask timerTask){
        service.update(id, timerTask);
    }

    @DeleteMapping("/timers/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    
    

}
