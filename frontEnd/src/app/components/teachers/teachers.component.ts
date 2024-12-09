import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.scss']
})
export class TeachersComponent implements OnInit {
  teachers: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getTeachers().subscribe(data => {
      this.teachers = data;
    });
  }
}
