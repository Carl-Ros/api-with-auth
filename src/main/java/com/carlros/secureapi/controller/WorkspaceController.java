package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
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
    public void create(@RequestBody Workspace workspace) {
        service.create(workspace);
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
