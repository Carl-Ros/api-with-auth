package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.Appointment;
import com.carlros.secureapi.model.Workspace;
import com.carlros.secureapi.service.AppointmentService;
import com.carlros.secureapi.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final WorkspaceService workspaceService;

    @GetMapping("/workspaces/{workspaceId}/appointments")
    public List<Appointment> readAll(@PathVariable Long workspaceId) {
        return appointmentService.all(workspaceId);
    }

    @GetMapping("/workspaces/{workspaceId}/appointments/{id}")
    public Appointment readOne(@PathVariable Long workspaceId, @PathVariable Long id) {
        return appointmentService.one(workspaceId, id);
    }

    @PostMapping("/workspaces/{workspaceId}/appointments")
    public void create(@PathVariable Long workspaceId, @RequestBody Appointment appointment){
        Workspace workspace = workspaceService.one(workspaceId);
        appointmentService.create(workspace, appointment);
    }

    @PatchMapping("/workspaces/{workspaceId}/appointments/{id}")
    public void update(@PathVariable Long workspaceId, @PathVariable Long id,@RequestBody Appointment appointment) {
        appointmentService.update(workspaceId, id, appointment);
    }

    @DeleteMapping("/workspaces/{workspaceId}/appointments/{id}")
    public void delete(@PathVariable Long id){
        appointmentService.delete(id);
    }
}
