package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.ConsumableItem;
import com.carlros.secureapi.service.ConsumableItemService;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ConsumableItemController {
    private final ConsumableItemService service;

    @GetMapping("/consumables")
    public List<ConsumableItem> readAll(){
        return service.all();
    }

    @GetMapping("/consumables/{id}")
    public ConsumableItem readOne(@PathVariable  Long id){
        return service.one(id);
    }

    @PostMapping("/consumables")
    public void create(@RequestBody ConsumableItem consumableItem){
        service.create(consumableItem);
    }

    @PatchMapping("/consumables/{id}")
    public void update(@PathVariable Long id, @RequestBody ConsumableItem consumableItem){
        service.update(id, consumableItem);
    }

    @DeleteMapping("/consumables/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
