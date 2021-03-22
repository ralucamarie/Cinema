package Domain;

import Repository.IRepository;
import Repository.InMemoryRepository;

public class ReservationValidator {
    /**
     *
     * @param reservation
     * @param movieRepository
     * @throws Exception
     */
    public void validate (Reservation reservation, IRepository<Movie> movieRepository) throws Exception {
        Movie givenMovie = movieRepository.readOne(reservation.getIdmovie());
        if (givenMovie==null){
            throw new Exception("There is no Movie with the given ID");
        } else if (!givenMovie.isRunning()) {
            throw new Exception("You cannot add a reservation for a movie not running");
        }
    }

}
