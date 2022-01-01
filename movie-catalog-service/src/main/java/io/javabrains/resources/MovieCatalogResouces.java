package io.javabrains.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.model.CatalogItem;
import io.javabrains.model.Movie;
import io.javabrains.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouces {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder builder;

	public MovieCatalogResouces() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/{userId}")
	public ResponseEntity<Object> getCathalog(@PathVariable String userId) {
		
		//RestTemplate restTemplate = new RestTemplate();
		
		//WebClient.Builder builder = WebClient.builder();
		
		UserRating ratings = restTemplate.getForObject("http://movie-rating-service/raiting-data/users/"+userId, UserRating.class);
		
		List<CatalogItem> movies = ratings.getUsersRating().stream().map(rating -> { 
			//Movie movie = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
			Movie movie = builder.build()
							.get()
							.uri("http://movie-info-service/movies/"+rating.getMovieId())
							.retrieve()
							.bodyToMono(Movie.class)
							.block();
			return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(movies);
		//return Collections.singletonList(new CatalogItem("Spiderman", "No way home", 8));
	}
	
}
