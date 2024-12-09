package com.example.emploi_du_temps.Controller;

import com.example.emploi_du_temps.Service.AuthService;
import com.example.emploi_du_temps.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Méthode pour l'authentification de l'utilisateur
     * 
     * @param loginRequest Contient l'email et le mot de passe
     * @return Le token JWT si l'utilisateur est authentifié
     */
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            User user = authService.getUserByEmail(loginRequest.getEmail());
            String token = authService.generateToken(user);
            return ResponseEntity.ok(token); // Renvoie le token JWT dans la réponse
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    /**
     * Méthode pour l'inscription d'un utilisateur
     * 
     * @param user Contient l'email, le mot de passe et d'autres détails
     * @return L'utilisateur créé ou une erreur si l'email existe déjà
     */
    @PostMapping("/api/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (authService.userExists(user.getEmail())) {
            return ResponseEntity.status(400).body(null); // L'email existe déjà
        }
        user.setPassword(authService.encodePassword(user.getPassword())); // Encode le mot de passe
        authService.createUser(user); // Sauvegarde l'utilisateur dans la base de données
        return ResponseEntity.ok(user);
    }
}
