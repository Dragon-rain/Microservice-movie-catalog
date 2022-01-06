package io.javabrains.resouces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.model.Movie;
import io.javabrains.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieResource.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey; 
	
	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable String movieId) {
		
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
		LOGGER.info("Movie found: {}", movieSummary);
		return new Movie(movieId, movieSummary.getOverview(), movieSummary.getTitle());
	}

}
 