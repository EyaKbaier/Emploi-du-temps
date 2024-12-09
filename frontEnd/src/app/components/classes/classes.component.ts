import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.scss']
})
export class ClassesComponent implements OnInit {
  classes: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getClasses().subscribe({
      next: (data) => {
        console.log(data);
        this.classes = data;
      },
      error: (err) => {
        console.error("Erreur lors du chargement des classes", err);
      }
    });
  }  
}
