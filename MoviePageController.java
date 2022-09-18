package com.example.TheMovies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviePageController{
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@GetMapping(path = "/main/movie")
	public String movie(@RequestParam(name ="id", required=true) int id, Model model) {
		Optional<Movie> moviepage = movieRepository.findById(id);
		if (moviepage.isEmpty())
			return "Movie not found";
		model.addAttribute("movie", moviepage.get());
	
		
		return "MoviePage";
	}
	
}