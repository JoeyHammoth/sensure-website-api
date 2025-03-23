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

    @CrossOrigin(origins = "https://sensure-react-website.vercel.app",
            methods = {RequestMethod.POST, RequestMethod.OPTIONS},
            allowedHeaders = "*",
            allowCredentials = "true")
    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }

    @CrossOrigin(origins = "https://sensure-react-website.vercel.app")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
