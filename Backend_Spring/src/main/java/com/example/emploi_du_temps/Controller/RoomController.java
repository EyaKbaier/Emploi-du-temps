package com.example.emploi_du_temps.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.emploi_du_temps.Entity.Room;
import com.example.emploi_du_temps.Exception.ResourceNotFoundException;
import com.example.emploi_du_temps.Repository.RoomRepository;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    // Récupérer toutes les salles
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Récupérer une salle par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));
        return ResponseEntity.ok(room);
    }

    // Créer une nouvelle salle
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // Mettre à jour une salle existante
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));

        room.setName(roomDetails.getName());
        room.setCapacity(roomDetails.getCapacity());
        room.setBuilding(roomDetails.getBuilding());
        room.setFloor(roomDetails.getFloor());

        Room updatedRoom = roomRepository.save(room);
        return ResponseEntity.ok(updatedRoom);
    }

    // Supprimer une salle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));

        roomRepository.delete(room);
        return ResponseEntity.noContent().build();
    }
}