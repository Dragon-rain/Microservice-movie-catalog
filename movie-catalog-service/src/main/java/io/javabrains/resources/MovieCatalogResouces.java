package io.javabrains.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.model.CatalogItem;
import io.javabrains.model.UserRating;
import io.javabrains.service.MovieInfo;
import io.javabrains.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouces {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovieCatalogResouces.class);
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;

	public MovieCatalogResouces() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/{userId}")
	public ResponseEntity<Object> getCathalog(@PathVariable String userId) {
		
		//RestTemplate restTemplate = new RestTemplate();
		
		//WebClient.Builder builder = WebClient.builder();
		
		UserRating ratings = userRatingInfo.getUserRaring(userId);
		LOG.info("found reitings: {}", ratings);
		List<CatalogItem> movies = ratings.getUsersRating().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
		
		return ResponseEntity.ok(movies);
		//return Collections.singletonList(new CatalogItem("Spiderman", "No way home", 8));
	}
	
}
