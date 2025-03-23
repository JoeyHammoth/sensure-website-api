package org.sensure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepo contactRepository;

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }
}
