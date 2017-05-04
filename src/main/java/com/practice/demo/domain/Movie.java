package com.practice.demo.domain;

public class Movie {

	private int id;
	private String name;

	private String directorName;
	private String rating;
	private int movieInStock;
	private Artist artist;

	public Movie() {

	}

	public Movie(int id, String name, String directorName, String rating, int movieInStock, Artist artist) {
		this.id = id;
		this.name = name;
		this.directorName = directorName;
		this.rating = rating;
		this.movieInStock = movieInStock;
		this.artist = artist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getMovieInStock() {
		return movieInStock;
	}

	public void setMovieInStock(int movieInStock) {
		this.movieInStock = movieInStock;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}
