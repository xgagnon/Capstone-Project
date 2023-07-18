package controllers;

import exceptions.UserException;
import models.Image;
import models.User;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ImageService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private ImageService service = new ImageService();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Image> find(@PathVariable("id") int id) {
        return new ResponseEntity<Image>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Image> insert(@RequestBody Image image) throws UserException {
        return ResponseEntity.ok(this.service.insert(image));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Image image) {
        return ResponseEntity.ok(service.update(image));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Image image) {
        return ResponseEntity.ok(service.delete(image));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> findAll() {
        return new ResponseEntity<List<Document>>(service.findAll(), HttpStatus.OK);
    }
 }
