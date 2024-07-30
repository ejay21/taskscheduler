package org.example;

import org.example.model.ProjectPlan;
import org.example.model.Task;
import org.example.service.ProjectPlanService;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ProjectPlanService projectPlanService = new ProjectPlanService();
    public static void main(String[] args) {
        Task task1 = new Task("Task 1", 2);
        Task task2 = new Task("Task 2", 2);
        Task task3 = new Task("Task 3", 2);
//        Task task4 = new Task("Task 4", 4);

        task1.addDependency(task2);
        task1.addDependency(task3);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        ProjectPlan project = new ProjectPlan();
        project.getTasks().addAll(tasks);

        projectPlanService.calculateSchedule(project);
        projectPlanService.printSchedule(project);
    }
}