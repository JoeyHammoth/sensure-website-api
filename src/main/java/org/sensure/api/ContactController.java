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

    @Autowired
    private ContactRepo contactRepository;

    @CrossOrigin(origins = "https://sensure-react-website.vercel.app", maxAge = 3600)
    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        try {
            contactRepository.save(contact);
            return ResponseEntity.ok()
                    .header("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app")
                    .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                    .header("Access-Control-Allow-Headers", "*")
                    .body("Contact saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app")
                    .body("Error saving contact: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "https://sensure-react-website.vercel.app", maxAge = 3600)
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> handleOptions() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "*")
                .header("Access-Control-Max-Age", "3600")
                .body("CORS preflight request handled successfully");
    }

    @CrossOrigin(origins = "https://sensure-react-website.vercel.app", maxAge = 3600)
    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app")
                .body("API is working");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app")
                .body("Server error: " + e.getMessage());
    }
}