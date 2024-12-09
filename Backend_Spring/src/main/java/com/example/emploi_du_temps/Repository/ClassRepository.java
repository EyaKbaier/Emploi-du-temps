package com.example.emploi_du_temps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.emploi_du_temps.Entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
