package io.javabrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.model.CatalogItem;
import io.javabrains.model.Movie;
import io.javabrains.model.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		/*
		 * Movie movie = builder.build() .get()
		 * .uri("http://movie-info-service/movies/"+rating.getMovieId()) .retrieve()
		 * .bodyToMono(Movie.class) .block();
		 */
		return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
	}
	
	public CatalogItem getFallBackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}

}
