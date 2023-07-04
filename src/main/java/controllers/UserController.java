package controllers;

import exceptions.UserException;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service = new UserService();

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<User>find(@PathVariable("email") String email) {
        return new ResponseEntity<User>(service.find(email), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> insert(@RequestBody User user) throws UserException {
        return ResponseEntity.ok(this.service.insert(user));
    }
}
