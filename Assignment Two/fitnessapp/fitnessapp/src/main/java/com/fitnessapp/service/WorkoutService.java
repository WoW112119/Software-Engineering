package com.fitnessapp.service;

import com.fitnessapp.model.ExerciseLog;
import com.fitnessapp.repository.ExerciseLogRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    
    private final ExerciseLogRepository repository;

    public WorkoutService(ExerciseLogRepository repository) {
        this.repository = repository;
    }

    public void saveWorkout(ExerciseLog log) {
        if (log.getSets() <= 0 || log.getReps() <= 0 || log.getWeight() < 0) {
            throw new IllegalArgumentException("Sets, reps, and weight must be positive numbers.");
        }
        if (log.getExerciseName() == null || log.getExerciseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise name cannot be empty.");
        }
        repository.save(log);
    }
}