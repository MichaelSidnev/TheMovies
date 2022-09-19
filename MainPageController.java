package com.example.TheMovies;

import java.sql.Blob;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.TheMovies.MovieRepository;

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

	@PostMapping(path = "/main/addApi") // Map ONLY POST Requests
	public @ResponseBody String addNewMovie(@RequestParam String name, @RequestParam Integer kapibara) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Movie n = new Movie();
		n.setName(name);
		n.setKapibara(kapibara);
		//n.setImage(image);
		movieRepository.save(n);
		return "Saved";
	}
	
	@GetMapping("/main/edit")
	public String showForm(Movie movie) {
		return "MovieAdd";
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