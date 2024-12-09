import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  getRooms(): Observable<any> {
    return this.http.get(`${this.apiUrl}/rooms`);
  }

  getTeachers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/teachers`);
  }

  getSubjects(): Observable<any> {
    return this.http.get(`${this.apiUrl}/subjects`);
  }

  getClasses(): Observable<any> {
    return this.http.get(`${this.apiUrl}/classes`);
  }

  getSessions(): Observable<any> {
    return this.http.get(`${this.apiUrl}/sessions`);
  }

  getStudents(): Observable<any> {
    return this.http.get(`${this.apiUrl}/students`);
  }

  addRoom(room: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/rooms`, room);
  }

  updateRoom(roomId: string, room: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/rooms/${roomId}`, room);
  }

  deleteRoom(roomId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/rooms/${roomId}`);
  }

  addTeacher(teacher: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/teachers`, teacher);
  }

  updateTeacher(teacherId: string, teacher: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/teachers/${teacherId}`, teacher);
  }

  deleteTeacher(teacherId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/teachers/${teacherId}`);
  }
}
