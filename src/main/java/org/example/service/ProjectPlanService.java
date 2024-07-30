package org.example.service;

import org.example.model.ProjectPlan;
import org.example.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProjectPlanService {

    public void calculateSchedule(ProjectPlan project) {
        List<Task> tasks = project.getTasks();
        Map<Task, Integer> inDegree = new HashMap<>();
        for (Task task : tasks) {
            inDegree.put(task, 0);
        }
        for (Task task : tasks) {
            for (Task dep : task.getDependencies()) {
                inDegree.put(task, inDegree.get(task) + 1);
            }
        }

        Queue<Task> queue = new LinkedList<>();
        for (Task task : tasks) {
            if (inDegree.get(task) == 0) {
                queue.add(task);
            }
        }

        while (!queue.isEmpty()) {
            Task currentTask = queue.poll();
            LocalDate earliestStart = LocalDate.now();
            for (Task dep : currentTask.getDependencies()) {
                if (dep.getEndDate() != null && (earliestStart.isBefore(dep.getEndDate()) || earliestStart.isEqual(dep.getEndDate()))) {
                    earliestStart = dep.getEndDate().plusDays(1);
                }
            }
            currentTask.setStartDate(earliestStart);
            currentTask.setEndDate(currentTask.getStartDate().plusDays(currentTask.getDuration() - 1));

            for (Task task : tasks) {
                if (task.getDependencies().contains(currentTask)) {
                    inDegree.put(task, inDegree.get(task) - 1);
                    if (inDegree.get(task) == 0) {
                        queue.add(task);
                    }
                }
            }
        }
    }

    public void printSchedule(ProjectPlan project) {
        List<Task> tasks = project.getTasks();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String leftAlignFormat = "| %-10s | %-10s | %-10s | %-10s |%n";

        System.out.format("+------------+------------+------------+------------+%n");
        System.out.format("| Task Name  | Start Date | End Date   | Duration   |%n");
        System.out.format("+------------+------------+------------+------------+%n");

        for (Task task : tasks) {
            String startDate = task.getStartDate() != null ? task.getStartDate().format(formatter) : "N/A";
            String endDate = task.getEndDate() != null ? task.getEndDate().format(formatter) : "N/A";
            System.out.format(leftAlignFormat, task.getName(), startDate, endDate, task.getDuration());
        }

        System.out.format("+------------+------------+------------+------------+%n");
    }
}
