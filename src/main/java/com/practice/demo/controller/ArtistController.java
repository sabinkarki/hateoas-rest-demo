package com.practice.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.practice.demo.domain.Artist;
import com.practice.demo.service.MovieService;
import com.practice.demo.util.ServiceResponse;

@RestController
public class ArtistController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/artist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getArtist(@PathVariable(value = "id") int id) {
		Artist artist = movieService.getArtist(id);
		ServiceResponse response;
		if (artist != null) {
			response = new ServiceResponse(true, "Artist with id: " + id);
			Resource<Artist> resource = new Resource<Artist>(artist);
			resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
			resource.add(linkTo(methodOn(MovieController.class).findAllMovies()).withRel("Movies"));
			response.addParam(artist.getId() + "", resource);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		} else {
			response = new ServiceResponse(false, "Artist with id: " + id + " not found");
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.NOT_FOUND);
		}
	}

}
