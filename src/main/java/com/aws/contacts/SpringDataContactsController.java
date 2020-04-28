/**
 * 
 */
package com.aws.contacts;

import com.aws.contacts.domain.Contact;
import com.aws.contacts.domain.ContactRepository;
import com.aws.contacts.service.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * @author coder983
 *
 */

@Controller
@RequestMapping("/")
public class SpringDataContactsController {
	
	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	ContactServices service;
	
	@RequestMapping
	public String viewContacts(ModelMap model) {
		
		service.ListContacts(model);
				
		return "mainview";
	}
	
	@RequestMapping("/add")
	public String addContact(ModelMap model){
		
		service.loadStates(model);
		service.setupAdd(model);
		
		return "addcontact";
	}
	
	@PostMapping("/create")
	public String createContact(@ModelAttribute Contact contact, ModelMap model) {
		
		contactRepo.save(contact);
		service.ListContacts(model);
		return "mainview";
	}

	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable("id") long id, ModelMap model){
		Contact contact = contactRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id: " + id));
		contactRepo.delete(contact);
		model.addAttribute("contacts", contactRepo.findAll());
		return "mainview";
	}

}
