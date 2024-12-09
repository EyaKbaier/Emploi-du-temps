import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss']
})
export class SessionsComponent implements OnInit {
  sessions: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getSessions().subscribe(data => {
      this.sessions = data;
    });
  }
}
