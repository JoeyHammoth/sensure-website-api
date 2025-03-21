package org.sensure.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.sensure.api.Contact;

public interface ContactRepo extends MongoRepository<Contact, String> {
    // Custom query methods (if needed) can be added here.
}
