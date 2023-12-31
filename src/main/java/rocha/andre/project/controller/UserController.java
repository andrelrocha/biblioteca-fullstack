package rocha.andre.project.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocha.andre.project.domain.user.DTO.SaldoDTO;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserLoginDTO;
import rocha.andre.project.domain.user.DTO.UserUpdateDTO;
import rocha.andre.project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity performLogin(@RequestBody @Valid UserLoginDTO data) {
        var tokenJwt = userService.performLogin(data);
        return ResponseEntity.ok(tokenJwt);
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserDTO data) {
        var newUser = userService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/listall")
    public ResponseEntity listAllUsers() {
        var users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{tokenJWT}")
    public ResponseEntity listUserById(@PathVariable String tokenJWT) {
        var user = userService.listUserById(tokenJWT);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{tokenJWT}")
    @Transactional
    public ResponseEntity updateUser(@RequestBody UserUpdateDTO data, @PathVariable String tokenJWT) {
        var updatedUser = userService.updateUser(data, tokenJWT);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{tokenJWT}")
    @Transactional
    public ResponseEntity addSaldo(@PathVariable String tokenJWT, @RequestBody SaldoDTO data) {
        var stringSuccess = userService.addSaldo(data, tokenJWT);
        return ResponseEntity.ok(stringSuccess);
    }

}
