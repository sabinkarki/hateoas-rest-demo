package com.practice.demo.service.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.domain.Artist;
import com.practice.demo.domain.Movie;
import com.practice.demo.repository.repository.MovieRepository;
import com.practice.demo.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Artist getArtist(String id) {
		return movieRepository.getArtist(id);
	}

	@Override
	public Movie getMovies(int id) {
		return movieRepository.getMovies(id);
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		return movieRepository.getAllMovies();
	}

}
