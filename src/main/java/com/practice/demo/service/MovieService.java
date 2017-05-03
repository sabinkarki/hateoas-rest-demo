package com.practice.demo.service;

import java.util.Map;

import com.practice.demo.domain.Artist;
import com.practice.demo.domain.Movie;

public interface MovieService {
	
	public Artist getArtist(final String id);
	public Movie getMovies(int id);
	public Map<Integer, Movie> getAllMovies();

}
