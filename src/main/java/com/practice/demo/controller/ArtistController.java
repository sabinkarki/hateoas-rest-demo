package com.practice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.domain.Artist;
import com.practice.demo.service.MovieService;

@RestController
public class ArtistController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/artist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Artist> getArtist(@PathVariable(value = "id") int id) {

		return null;
	}

}
