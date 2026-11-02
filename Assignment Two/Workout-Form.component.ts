import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-workout-form',
  templateUrl: './workout-form.component.html',
  styleUrls: ['./workout-form.component.css']
})
export class WorkoutFormComponent {
  exerciseName: string = '';
  sets: number | null = null;
  reps: number | null = null;
  weight: number | null = null;
  statusMessage: string = '';
  isError: boolean = false;

  constructor(private http: HttpClient) {}

  submitWorkout() {
    const payload = {
      exerciseName: this.exerciseName,
      sets: this.sets,
      reps: this.reps,
      weight: this.weight
    };

    this.http.post('http://localhost:8080/api/workouts/log', payload, { responseType: 'text' })
      .subscribe({
        next: (res) => {
          this.statusMessage = 'Success: ' + res;
          this.isError = false;
          this.resetForm();
        },
        error: (err) => {
          this.statusMessage = 'Error: ' + err.error;
          this.isError = true;
        }
      });
  }

  resetForm() {
    this.exerciseName = '';
    this.sets = null;
    this.reps = null;
    this.weight = null;
  }
}