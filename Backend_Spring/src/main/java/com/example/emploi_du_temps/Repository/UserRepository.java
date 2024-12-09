package com.example.emploi_du_temps.Repository;

import com.example.emploi_du_temps.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour rechercher un utilisateur par son email
    User findByEmail(String email);
}
