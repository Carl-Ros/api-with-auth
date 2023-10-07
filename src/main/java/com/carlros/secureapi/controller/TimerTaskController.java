package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.TimerTask;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.service.TimerTaskService;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TimerTaskController {
    private final TimerTaskService timerTaskService;
    private final WorkspaceService workspaceService;

    @GetMapping("/workspaces/{workspaceId}/timers")
    public List<TimerTask> readAll(@PathVariable Long workspaceId) {
        return timerTaskService.all(workspaceId);
    }

    @GetMapping("/workspaces/{workspaceId}/timers/{id}")
    public TimerTask readOne(@PathVariable Long workspaceId, @PathVariable Long id) {
        return timerTaskService.one(workspaceId, id);
    }

    @PostMapping("/workspaces/{workspaceId}/timers")
    @ResponseStatus(HttpStatus.CREATED)
    public TimerTask create(@PathVariable Long workspaceId, @RequestBody TimerTask timerTask){
        Workspace workspace = workspaceService.one(workspaceId);
        return timerTaskService.create(workspace, timerTask);
    }

    @PatchMapping("/workspaces/{workspaceId}/timers/{id}")
    public void update(@PathVariable Long workspaceId, @PathVariable Long id,@RequestBody TimerTask timerTask) {
        timerTaskService.update(workspaceId, id, timerTask);
    }

    @DeleteMapping("/workspaces/{workspaceId}/timers/{id}")
    public void delete(@PathVariable Long id){
        timerTaskService.delete(id);
    }
}
