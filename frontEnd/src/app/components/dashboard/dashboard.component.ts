import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  stats: any = {};

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getRooms().subscribe(rooms => this.stats.rooms = rooms.length);
    this.apiService.getTeachers().subscribe(teachers => this.stats.teachers = teachers.length);  }
}
