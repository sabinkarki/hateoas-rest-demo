package com.practice.demo.repository.repository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.practice.demo.domain.Artist;
import com.practice.demo.domain.Movie;

@Repository
public class MovieRepository {
	private Map<Integer, Movie> movies;
	private Map<Integer, Artist> artists;

	public MovieRepository() {
		movies = new HashMap<>();
		artists = new HashMap<>();
		addArtist();

	}

	public void addMovies() {
		Movie movie1 = new Movie(1, "Part of Me", "Dan Cutforth and Jane Lipsitz", "5.9/10", 3, artists.get(1));
		Movie movie2 = new Movie(2, "Titanic", "James Cameron", "7.7/10", 2, artists.get(2));
		Movie movie3 = new Movie(3, "Charming", "Ross Venokur", "6.7/10", 1, artists.get(3));

		this.movies.put(movie1.getId(), movie1);
		this.movies.put(movie2.getId(), movie2);
		this.movies.put(movie3.getId(), movie3);

	}

	public void addArtist() {
		Artist artist1 = new Artist(1, "Kate", "Perry");
		Artist artist2 = new Artist(2, "Leonardo", "Dicaprio");
		Artist artist3 = new Artist(3, "Demi", "Lovato");

		this.artists.put(artist1.getId(), artist1);
		this.artists.put(artist2.getId(), artist2);
		this.artists.put(artist3.getId(), artist3);

		addMovies();

	}

	public Artist getArtist(final String id) {
		return this.artists.get(id);
	}

	public Movie getMovies(int id) {
		return this.movies.get(id);
	}

	public Map<Integer, Movie> getAllMovies() {
		return this.movies;
	}

}
