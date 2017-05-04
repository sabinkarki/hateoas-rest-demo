package com.practice.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.domain.Movie;
import com.practice.demo.service.MovieService;
import com.practice.demo.util.ServiceResponse;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> findAllMovies() {
		List<Movie> movies = movieService.getAllMovies();
		if (!movies.isEmpty()) {
			List<Resource<Movie>> movieResource = new ArrayList<Resource<Movie>>();
			ServiceResponse response = new ServiceResponse(true, "Movies in the List");
			movies.forEach(movie -> {
				movieResource.add(getMovieResourse(movie));
			});
			response.addParam("lstMovies", movieResource);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		}

		ServiceResponse response = new ServiceResponse(false, "There is no any movies in the List");
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> findMovieById(@PathVariable("id") int id) {
		Optional<Movie> movies = movieService.getMovies(id);
		ServiceResponse response;

		if (movies.isPresent()) {
			response = new ServiceResponse(true, "Movie with corresponding id");
		} else {
			response = new ServiceResponse(false, "Movie with corresponding id not available");
		}

		return movies.map(movie -> {
			List<Resource<Movie>> movieResource = new ArrayList<Resource<Movie>>();
			movieResource.add(getMovieResourse(movie));
			response.addParam(movie.getName(), movieResource);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		}).orElse(new ResponseEntity<ServiceResponse>(response, HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/movie/buy/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> buyMovie(@PathVariable(value = "id") int id) {
		ServiceResponse response;
		Optional<Movie> movies = movieService.getMovies(id);

		if (!movies.isPresent()) {
			response = new ServiceResponse(false, "Movie with id " + " " + id + " " + "not available");
		}

		else if (movies.get().getMovieInStock() == 0) {
			response = new ServiceResponse(false, "Not in a stock");
			Resource<Movie> movieResource = getMovieResourse(movies.get().getId());
			response.addParam("OutOfStock", movieResource);
		}

		else {
			response = new ServiceResponse(true, "You have purchased");
		}

		return movies.filter(movie -> (movies.get().getMovieInStock() > 0)).map(movie -> {
			Resource<Movie> movieResource = new Resource<>(movie);
			movie.setMovieInStock(movie.getMovieInStock() - 1);
			movieResource.add(linkTo(methodOn(MovieController.class).buyMovie(id)).withSelfRel());
			response.addParam(movie.getName(), movieResource);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		}).orElse(new ResponseEntity<ServiceResponse>(response, HttpStatus.NOT_FOUND));
	}

	private <T> Resource<Movie> getMovieResourse(T t) {
		if (t instanceof Movie) {
			Movie movie = (Movie) t;
			Resource<Movie> resource = new Resource<Movie>(movie);
			// Link To Movie
			resource.add(linkTo(methodOn(MovieController.class).findMovieById(movie.getId())).withSelfRel());
			// Link To Artist
			resource.add(
					linkTo(methodOn(ArtistController.class).getArtist(movie.getArtist().getId())).withRel("Artist"));

			if (movie.getMovieInStock() > 0) {
				resource.add(linkTo(methodOn(MovieController.class).buyMovie(movie.getId())).withRel("Buy Movie"));
			}
			return resource;
		} else {
			Optional<Movie> movies = movieService.getMovies((int) t);
			Resource<Movie> resource = new Resource<Movie>(movies.get());
			resource.add(linkTo(methodOn(MovieController.class).findAllMovies()).withRel("Artist"));
			return resource;

		}
	}

}
