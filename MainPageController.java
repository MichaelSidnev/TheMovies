package com.example.TheMovies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	

    

	
	

}