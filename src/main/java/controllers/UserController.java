package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<User>find(@PathVariable("email") String email) {
        return new ResponseEntity<User>(service.find(email), HttpStatus.OK);
    }
}
