package br.com.springbootdatajpamysqqlwebapimaster.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.springbootdatajpamysqqlwebapimaster.model.Tutorial;
import br.com.springbootdatajpamysqqlwebapimaster.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;

    @GetMapping("/tutoriais")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String titulo) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (titulo == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTituloContaining(titulo).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    	

    @GetMapping("/tutoriais/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @PostMapping("/tutoriais")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitulo(), tutorial.getDescricao(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/tutoriais/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitulo(tutorial.getTitulo());
			_tutorial.setDescricao(tutorial.getDescricao());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/tutoriais/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @DeleteMapping("/tutoriais")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

    @GetMapping("/tutoriais/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
