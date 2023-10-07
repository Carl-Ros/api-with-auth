package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService service;

    @GetMapping("/workspaces")
    public List<Workspace> readAll(){
        return service.all();
    }

    @GetMapping("/workspaces/{id}")
    public Workspace readOne(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping("/workspaces")
    @ResponseStatus(HttpStatus.CREATED)
    public Workspace create(@RequestBody Workspace workspace) {
        return service.create(workspace);
    }

    @PatchMapping("/workspaces/{id}")
    public void update(@PathVariable Long id, @RequestBody Workspace workspace){
        service.update(id, workspace);
    }

    @DeleteMapping("/workspaces/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
