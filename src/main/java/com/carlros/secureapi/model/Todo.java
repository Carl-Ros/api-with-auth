package com.carlros.secureapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Todo {
    private @Id @GeneratedValue Long id;
    private @NotNull @NotEmpty String name;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    @JsonIgnore
    private Workspace workspace;

    public void updateInheritedAttributes(Todo todo) {
        if(todo.getName() != null){
            setName(todo.getName());
        }

        if(todo.getImageUrl() != null){
            setImageUrl(todo.getImageUrl());
        }
    }
}
