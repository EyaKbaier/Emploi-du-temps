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

import com.example.emploi_du_temps.Repository.ClassRepository;
import com.example.emploi_du_temps.Entity.Class;
import com.example.emploi_du_temps.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    private ClassRepository classRepository;

    @GetMapping
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable Long id) {
        Class schoolClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id " + id));
        return ResponseEntity.ok(schoolClass);
    }

    @PostMapping
    public Class createClass(@RequestBody Class Class) {
        return classRepository.save(Class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @RequestBody Class classDetails) {
        Class Class = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id " + id));

        Class.setName(classDetails.getName());
        Class.setSubject(classDetails.getSubject());
        Class.setStudents(classDetails.getStudents());

        Class updatedClass = classRepository.save(Class);
        return ResponseEntity.ok(updatedClass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        Class Class = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id " + id));

        classRepository.delete(Class);
        return ResponseEntity.noContent().build();
    }
}
