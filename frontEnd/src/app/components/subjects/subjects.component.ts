import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {
  subjects: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getSubjects().subscribe(data => {
      this.subjects = data;
    });
  }
}
