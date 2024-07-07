package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.LoginDTO;
import ifsul.io.IFMeet.payload.response.JwtResponse;
import ifsul.io.IFMeet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/v1/autenticacao")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fazLogin(@Valid @RequestBody LoginDTO loginDTO) {
        JwtResponse token = authService.fazLogin(loginDTO);
        return ResponseEntity.ok(token);
    }
}