package com.practice.demo.service;

import java.util.List;
import java.util.Optional;

import com.practice.demo.domain.Artist;
import com.practice.demo.domain.Movie;

public interface MovieService {
	
	public Artist getArtist(int id);
	public Optional <Movie> getMovies(int id);
	public List<Movie> getAllMovies();

}
