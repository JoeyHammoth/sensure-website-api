package org.sensure.api;

import org.sensure.api.Contact;
import org.sensure.api.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private static final String ALLOWED_ORIGIN = "https://sensure-react-website.vercel.app";

    @Autowired
    private ContactRepo contactRepository;

    @CrossOrigin(
            origins = ALLOWED_ORIGIN,
            allowedHeaders = "*",
            methods = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET},
            maxAge = 3600
    )
    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        try {
            contactRepository.save(contact);
            return ResponseEntity.ok("Contact saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving contact: " + e.getMessage());
        }
    }

    // This is essential for handling preflight OPTIONS requests
    @CrossOrigin(
            origins = ALLOWED_ORIGIN,
            allowedHeaders = "*",
            methods = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET},
            maxAge = 3600
    )
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Server error: " + e.getMessage());
    }
}