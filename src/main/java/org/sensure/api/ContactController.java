package org.sensure.api;

import org.sensure.api.Contact;
import org.sensure.api.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "https://sensure-react-website.vercel.app")
public class ContactController {

    @Autowired
    private ContactRepo contactRepository;

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }

    // ✅ Explicitly handle preflight OPTIONS requests
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "https://sensure-react-website.vercel.app");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        headers.add("Access-Control-Allow-Credentials", "true");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
