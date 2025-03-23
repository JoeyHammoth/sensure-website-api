package org.sensure.api;

import org.sensure.api.Contact;
import org.sensure.api.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "https://sensure-react-website.vercel.app",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*",
        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"},
        allowCredentials = "true")
public class ContactController {

    @Autowired
    private ContactRepo contactRepository;

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add a GET endpoint for testing
    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("API is working");
    }
}