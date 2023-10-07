package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.ConsumableItem;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.service.ConsumableItemService;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumableItemController {
    private final ConsumableItemService consumableService;
    private final WorkspaceService workspaceService;

    @GetMapping("/workspaces/{workspaceId}/consumables")
    public List<ConsumableItem> readAll(@PathVariable Long workspaceId) {
        return consumableService.all(workspaceId);
    }

    @GetMapping("/workspaces/{workspaceId}/consumables/{id}")
    public ConsumableItem readOne(@PathVariable Long workspaceId, @PathVariable Long id) {
        return consumableService.one(workspaceId, id);
    }

    @PostMapping("/workspaces/{workspaceId}/consumables")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumableItem create(@PathVariable Long workspaceId, @RequestBody ConsumableItem consumable){
        Workspace workspace = workspaceService.one(workspaceId);
        return consumableService.create(workspace, consumable);
    }

    @PatchMapping("/workspaces/{workspaceId}/consumables/{id}")
    public void update(@PathVariable Long workspaceId, @PathVariable Long id,@RequestBody ConsumableItem consumable) {
        consumableService.update(workspaceId, id, consumable);
    }

    @DeleteMapping("/workspaces/{workspaceId}/consumables/{id}")
    public void delete(@PathVariable Long id){
        consumableService.delete(id);
    }
}
