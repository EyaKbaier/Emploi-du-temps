import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { ClassesComponent } from './components/classes/classes.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { StudentsComponent } from './components/students/students.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'rooms', component: RoomsComponent },
  { path: 'teachers', component: TeachersComponent },
  { path: 'subjects', component: SubjectsComponent },
  { path: 'classes', component: ClassesComponent },
  { path: 'sessions', component: SessionsComponent },
  { path: 'students', component: StudentsComponent },
  { path: '', redirectTo: '/rooms', pathMatch: 'full' },
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
