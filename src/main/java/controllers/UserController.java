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

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public ResponseEntity<User>find(@PathVariable("uid") String uid) {
        return new ResponseEntity<User>(service.find(uid), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> insert(@RequestBody User user) throws UserException {
        return ResponseEntity.ok(this.service.insert(user));
    }

    //insert many

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody User user) {
        return ResponseEntity.ok(service.update(user));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody User user) {
        return ResponseEntity.ok(service.delete(user));
    }
 }
