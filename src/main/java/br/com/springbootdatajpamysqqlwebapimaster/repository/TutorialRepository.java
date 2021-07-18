package br.com.springbootdatajpamysqqlwebapimaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springbootdatajpamysqqlwebapimaster.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>   {
    List<Tutorial> findByPublished(boolean published);
	List<Tutorial> findByTituloContaining(String titulo);
}