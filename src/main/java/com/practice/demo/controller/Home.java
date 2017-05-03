package com.practice.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practice.demo.domain.Movie;
import com.practice.demo.service.MovieService;

@Controller
public class Home {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "index";
	}

}
