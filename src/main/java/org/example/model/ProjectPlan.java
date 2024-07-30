package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ProjectPlan {
    List<Task> tasks;

    public ProjectPlan() {
        tasks = new ArrayList<>();
    }

}