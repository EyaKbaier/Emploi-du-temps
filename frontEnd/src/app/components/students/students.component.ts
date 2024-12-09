import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {
  students: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getStudents().subscribe(data => {
      this.students = data;
    });
  }
}
