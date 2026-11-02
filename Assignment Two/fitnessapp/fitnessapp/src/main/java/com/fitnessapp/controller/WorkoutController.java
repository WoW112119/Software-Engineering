package com.fitnessapp.controller;

import com.fitnessapp.model.ExerciseLog;
import com.fitnessapp.service.WorkoutService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkoutController {
    
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/log")
    public ResponseEntity<String> logWorkout(@RequestBody ExerciseLog log) {
        try {
            workoutService.saveWorkout(log);
            return ResponseEntity.ok("Workout logged successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid data: " + e.getMessage());
        }
    }
}