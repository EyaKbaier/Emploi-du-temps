package com.example.emploi_du_temps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.emploi_du_temps.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
