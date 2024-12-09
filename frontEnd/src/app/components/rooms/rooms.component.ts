import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {
  rooms: any[] = [];
  newRoom = { room_id: '', room_name: '', capacity: 0, building: '', floor: 0 };

  constructor(private apiService: ApiService) {}

  loadRooms() {
    this.apiService.getRooms().subscribe(data => {
      this.rooms = data;
    });
  }

  addRoom() {
    if (this.newRoom.room_id && this.newRoom.room_name) {
      this.apiService.addRoom(this.newRoom).subscribe({
        next: (_response) => {
          this.loadRooms(); 
          this.newRoom = { room_id: '', room_name: '', capacity: 0, building: '', floor: 0 }; // Réinitialisation du formulaire
        },
        error: (err) => {
          console.error("Erreur lors de l'ajout de la salle", err);
        }
      });
    }
  }  

  deleteRoom(roomId: string) {
    this.apiService.deleteRoom(roomId).subscribe(() => {
      this.loadRooms(); 
    });
  }

  ngOnInit(): void {
    this.apiService.getRooms().subscribe(
      (data) => {
        this.rooms = data;
      },
      (error) => {
        console.error('Error fetching rooms:', error);
      }
    );
  }
}
