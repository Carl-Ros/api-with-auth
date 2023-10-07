package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.IdRequest;
import com.carlros.secureapi.model.User;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.model.WorkspaceMembership;
import com.carlros.secureapi.service.UserService;
import com.carlros.secureapi.service.WorkspaceMembershipService;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkspaceMembershipController {
    private final WorkspaceMembershipService membershipService;
    private final WorkspaceService workspaceService;
    private final UserService userService;

    @GetMapping("/memberships")
    public List<WorkspaceMembership> readAll(){
        return membershipService.all();
    }

    @GetMapping("/memberships/{id}")
    public WorkspaceMembership readOne(@PathVariable Long id) {
        return membershipService.one(id);
    }

    @DeleteMapping("/memberships/{id}")
    public void delete(@PathVariable Long id){
        membershipService.delete(id);
    }

    @GetMapping("workspaces/{workspaceId}/memberships")
    public List<User> usersByWorkspace(@PathVariable Long workspaceId){
        return membershipService.workspaceMembers(workspaceId);
    }

    @PostMapping("workspaces/{workspaceId}/memberships")
    public void create(@PathVariable Long workspaceId, @RequestBody IdRequest idRequest){
        Workspace workspace = workspaceService.one(workspaceId);
        User user = userService.one(idRequest.getId());
        membershipService.addMember(workspace, user);
    }

    @GetMapping("/users/{userId}/workspaces")
    public List<Workspace> workspacesByUser(@PathVariable Long userId){
        return membershipService.userWorkspaces(userId);
    }

    @PostMapping("/users/{userId}/workspaces")
    @ResponseStatus(HttpStatus.CREATED)
    public Workspace createUserWorkspace(@PathVariable Long userId, @RequestBody Workspace workspace){
        User user = userService.one(userId);
        workspaceService.create(workspace);
        membershipService.addMember(workspace, user);
        return workspace;
    }

}
