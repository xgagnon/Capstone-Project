package controllers;

import exceptions.TransactionException;
import exceptions.UserException;
import models.Image;
import models.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.TransactionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService service = new TransactionService();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> find(@PathVariable("id") int id) {
        return new ResponseEntity<Transaction>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Transaction> insert(@RequestBody Transaction transaction) throws UserException, TransactionException {
        return ResponseEntity.ok(this.service.insert(transaction));
    }

    @RequestMapping(value = "/getAll/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> findAll(@PathVariable("email") String email) {
        return new ResponseEntity<List<Transaction>>(service.findAll(email), HttpStatus.OK);
    }
 }
