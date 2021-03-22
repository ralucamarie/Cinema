package Service;

import Domain.Movie;
import Domain.MovieValidator;
import Domain.Reservation;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Repository.RepositoryException;

import java.util.*;

public class MovieService {
    private IRepository<Movie> movieRepository;
    private MovieValidator movieValidator;
    private IRepository<Reservation> reservationRepository;

    /**
     * Movie Service Constructor
     * @param movieRepository
     * @param movieValidator
     */
    public MovieService(InMemoryRepository movieRepository, MovieValidator movieValidator, InMemoryRepository reservationRepository) {
        this.movieValidator = movieValidator;
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * Adds a Movie to the Movie Repository
     * @param id - The ID of the Movie (unique).
     * @param title The Movie Title.
     * @param year the year of the movie release
     * @param ticketPrice ticket price for the movie/person/reservation.
     * @param running True or false. If true, the movie is running therefore you can create reservation. If false, you cannot create reservation
     * @throws Exception throw exception if the movie was not validated and therefore not added to the repository.
     */
    public void addMovie (int id, String title, int year, Integer ticketPrice, boolean running) throws Exception {
        Movie movie = new Movie (id, title, year, ticketPrice, running);
        this.movieValidator.validate(movie);
        this.movieRepository.create(movie);
    }

    /**
     * Updates certain details of a movie. The details are overwritten to an existing movie.
     * @param id Id of the movie to update.
     * @param title Title of the movie. If different, it will be overwritten.
     * @param year Year of the movie. If different, it will be overwritten.
     * @param ticketPrice Ticket price of the movie. If different, it will be overwritten.
     * @param running True or false. If different, it will be overwritten.
     * @throws RepositoryException if the movie doesn't exist in the list.
     */
    public void updateMovie (int id, String title, int year, Integer ticketPrice, boolean running) throws Exception {
        Movie movie = new Movie (id, title, year, ticketPrice, running);
        this.movieValidator.validate(movie);
        this.movieRepository.update(movie);
    }

    /**
     * Returns a movie based on the ID entered by the user.
     * @param id of the movie.
     * @return All movie details based on the searched ID.
     * @throws Exception
     */
    public Movie returnMovie (int id) throws Exception {
        Movie movie = this.movieRepository.readOne(id);
        return movie;
    }

    /**
     * Remove a movie from the repository.
     * @param id Movie ID.
     * @param title Movie Title
     * @param year Movie release date
     * @param ticketPrice Ticket pricee
     * @param running True or False
     * @throws Exception returns exception if the movie is not on the list.
     */
    public void  deleteMovie (int id, String title, int year, Integer ticketPrice, boolean running) throws Exception {
        Movie movie = new Movie (id, title, year, ticketPrice, running);
        this.movieRepository.delete(movie.getId());
    }

    /**
     * Searches for all movies containing a string entered by the user.
     * @param keyword the string that will be searched for in the repository.
     * Displays on the screen the Movies containing the string.
     */
    public void searchEntity (String keyword){
        for (Movie movie : this.movieRepository.read()){
            if (movie.getTitle().toLowerCase().contains(keyword) || Integer.toString(movie.getId()).contains(keyword) || Integer.toString(movie.getYear()).contains(keyword) || Integer.toString(movie.getTicketPrice()).contains(keyword)){
                System.out.println(movie.toString());
            }
        }
    }

    /**
     * Goes through the full list of movies.
     * @return the repository list of movies.
     */
    public List<Movie> readAll (){
        return this.movieRepository.read();
    }




}
