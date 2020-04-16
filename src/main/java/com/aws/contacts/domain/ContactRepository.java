package com.aws.contacts.domain;

import com.aws.contacts.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
