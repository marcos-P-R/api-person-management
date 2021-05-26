package com.example.personapi.controller;

import com.example.personapi.dto.MessageResponseDTO;
import com.example.personapi.dto.request.PersonDTO;
import com.example.personapi.exception.PersonNotFoundException;
import com.example.personapi.model.Person;
import com.example.personapi.repository.PersonRepository;
import com.example.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

	@GetMapping
	public List<PersonDTO> listAll(){
		return personService.listAll();
	}

	@GetMapping("/{id}")
	public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteId(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}
}
