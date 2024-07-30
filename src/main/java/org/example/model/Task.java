package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Task {

    String name;
    int duration;
    List<Task> dependencies;
    LocalDate startDate;
    LocalDate endDate;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        dependencies.add(task);
    }

}