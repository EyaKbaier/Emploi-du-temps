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

import com.example.emploi_du_temps.Entity.Class;
import com.example.emploi_du_temps.Entity.Room;
import com.example.emploi_du_temps.Entity.Session;
import com.example.emploi_du_temps.Entity.Subject;
import com.example.emploi_du_temps.Entity.Teacher;
import com.example.emploi_du_temps.Exception.ResourceNotFoundException;
import com.example.emploi_du_temps.Repository.ClassRepository;
import com.example.emploi_du_temps.Repository.RoomRepository;
import com.example.emploi_du_temps.Repository.SessionRepository;
import com.example.emploi_du_temps.Repository.SubjectRepository;
import com.example.emploi_du_temps.Repository.TeacherRepository;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassRepository classRepository;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id " + id));
        return ResponseEntity.ok(session);
    }

    @PostMapping
    public Session createSession(@RequestBody Session sessionDetails) {
        // Vérifier que la salle, l'enseignant, le sujet et la classe existent
        Room room = roomRepository.findById(sessionDetails.getRoom().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        Teacher teacher = teacherRepository.findById(sessionDetails.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        Subject subject = subjectRepository.findById(sessionDetails.getSubject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        Class schoolClass = classRepository.findById(sessionDetails.getSchoolClass().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        sessionDetails.setRoom(room);
        sessionDetails.setTeacher(teacher);
        sessionDetails.setSubject(subject);
        sessionDetails.setSchoolClass(schoolClass);

        return sessionRepository.save(sessionDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session sessionDetails) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id " + id));

        session.setStartTime(sessionDetails.getStartTime());
        session.setEndTime(sessionDetails.getEndTime());
        session.setSessionDate(sessionDetails.getSessionDate());
        session.setRoom(sessionDetails.getRoom());
        session.setTeacher(sessionDetails.getTeacher());
        session.setSubject(sessionDetails.getSubject());
        session.setSchoolClass(sessionDetails.getSchoolClass());

        Session updatedSession = sessionRepository.save(session);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id " + id));

        sessionRepository.delete(session);
        return ResponseEntity.noContent().build();
    }
}
