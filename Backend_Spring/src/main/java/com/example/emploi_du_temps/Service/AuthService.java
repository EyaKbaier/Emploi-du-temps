package com.example.emploi_du_temps.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.emploi_du_temps.Entity.User;
import com.example.emploi_du_temps.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email); // Récupérer l'utilisateur par email
    }

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret); // Utilisation de la clé secrète
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", user.getRole())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(algorithm); // Signature avec la clé secrète
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encoder le mot de passe avant de sauvegarder
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password); // Utilisation de BCrypt pour encoder le mot de passe
    }
}
