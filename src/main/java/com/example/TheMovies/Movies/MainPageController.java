package com.example.TheMovies.Movies;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TheMovies.Users.User;

@Controller
public class MainPageController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping(path = "/main")
	public String movies(Model model) {
		Iterable<Movie> movies = movieRepository.findAll();
		model.addAttribute("movies", movies);
		return "MainPage";
	}

	@PostMapping(path = "/main/addApi")
	public String addNewMovie(@RequestParam String name, @RequestParam Integer premiere,
			@RequestParam MultipartFile file) throws IOException {

		Movie n = new Movie();
		n.setName(name);
		n.setPremiere(premiere);
		n.setType(file.getContentType());
		n.setData(file.getBytes());
		movieRepository.save(n);

		return "redirect:/main/movie?id=" + n.getId();
	}

	@GetMapping("/main/edit")
	public String showForm(Movie movie) {
		return "MovieAdd";
	}
	
	@GetMapping("/main/user/edit")
	public String showForm(User user) {
		return "UserEdit";
	}

	@PostMapping("/main/add")
	public String addMovie(@Valid Movie movie, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "MovieAdd";
		}

		movieRepository.save(movie);
		return "redirect:/main/movie?id=" + movie.getId();
	}

	@PostMapping("/delete")
	public String deleteMovie(@RequestParam(name = "id", required = true) int id) {
		movieRepository.deleteById(id);
		return "redirect:/main";
	}

}