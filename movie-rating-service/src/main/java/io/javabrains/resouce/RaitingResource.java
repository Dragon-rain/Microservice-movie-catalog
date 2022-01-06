package io.javabrains.resouce;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.model.Rating;
import io.javabrains.model.UserRating;

@RestController
@RequestMapping("/raiting-data")
public class RaitingResource {

	@RequestMapping("/{movieId}")
	private Rating getRaiting(@PathVariable String movieId) {		// TODO Auto-generated method stub
		return new Rating(movieId, 9);
	}
	
	@RequestMapping("/users/{userId}")
	private UserRating getUserRaiting(@PathVariable String userId) {		// TODO Auto-generated method stub
		
		return new UserRating(Arrays.asList(
				new Rating("3", 9),
				new Rating("8", 8),
				new Rating("9", 9)
		));
	}
	
}
