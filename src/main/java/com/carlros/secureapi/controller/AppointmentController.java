package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.Appointment;
import com.carlros.secureapi.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @GetMapping("/appointments")
    public List<Appointment> readAll() {
        return service.all();
    }

    @GetMapping("/appointments/{id}")
    public Appointment readOne(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping("/appointments")
    public void create(@RequestBody Appointment appointment){
        service.create(appointment);
    }

    @PatchMapping("/appointments/{id}")
    public void update(@PathVariable Long id, @RequestBody Appointment appointment) {
        service.update(id, appointment);
    }

    @DeleteMapping("/appointments/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
