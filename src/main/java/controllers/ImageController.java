package controllers;

import exceptions.UserException;
import models.Image;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ImageService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    private ImageService service = new ImageService();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Image> find(@PathVariable("id") Long id) {
        return new ResponseEntity<Image>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Image> insert(@RequestBody Image image) throws UserException {
        return ResponseEntity.ok(this.service.insert(image));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Image image) {
        return ResponseEntity.ok(service.update(image));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Image image) {
        return ResponseEntity.ok(service.delete(image));
    }
 }
