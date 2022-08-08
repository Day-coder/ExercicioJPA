package com.exercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercicio.entities.Livro;
import com.exercicio.repository.LivroRepository;

@Controller
@RequestMapping("/")
public class LivroController {
	
	@Autowired
	private LivroRepository repository;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@GetMapping("/consulta")
	public String consulta(Model model) {
		Iterable<Livro> livros= repository.findAll();
		model.addAttribute("livros", livros);
		return "consulta";
	}
	
	@GetMapping("/consulta/{idlivro}")
	public String editar(@PathVariable("idlivro")int id, Model model) {
		Livro livro= repository.findById(id).get();
		model.addAttribute("livro", livro);
		return "edicao";
	}
	
	@PostMapping("/cadastro")
	public String salvar(Livro livro) {
		repository.save(livro);
		return "redirect:/consulta";
	}
	@GetMapping("/deletar/{idlivro}")
	public String deletar(@PathVariable("idlivro")int id) {
		repository.deleteById(id);
		return "redirect:/consulta";
	}

}
