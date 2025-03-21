package org.sensure.api;

import org.sensure.api.Contact;
import org.sensure.api.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    @Autowired
    private ContactRepo contactRepository;

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }
}
