import Domain.MovieValidator;
import Domain.ReservationValidator;
import Repository.InMemoryRepository;
import Service.MovieService;
import Service.ReservationService;
import UserInterface.Console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        InMemoryRepository movieRepository = new InMemoryRepository();
        InMemoryRepository reservationRepository = new InMemoryRepository();
        MovieValidator movieValidator = new MovieValidator();
        ReservationValidator reservationValidator = new ReservationValidator();
        MovieService movieService = new MovieService(movieRepository,movieValidator,reservationRepository);
        ReservationService reservationService= new ReservationService(reservationRepository,movieRepository,reservationValidator);
        Console console = new Console(movieService,reservationService);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        movieService.addMovie(1, "The Night Before Christmas",2002,25,true);
        movieService.addMovie(2, "Cats and Dogs",2017,25,true);
        movieService.addMovie(3, "Beautiful Stranger at Night",2009,25,true);
        movieService.addMovie(4, "Everything about you",2020,25,true);
        movieService.addMovie(5, "Sailing",2014,25,true);
        movieService.addMovie(6, "Good night, I say to you",2014,25,true);

        reservationService.addReservation(11,1,4442321, dateFormat.parse("2020/12/12 13:20:12"));
        reservationService.addReservation(12,3,9965412, dateFormat.parse("2020/12/12 14:20:12"));
        reservationService.addReservation(13,3,2251422, dateFormat.parse("2020/12/12 15:20:12"));
        reservationService.addReservation(14,5,4442321, dateFormat.parse("2020/12/12 15:50:12"));
        reservationService.addReservation(15,6,4442321, dateFormat.parse("2020/12/12 13:20:12"));
        reservationService.addReservation(16,3,9965412, dateFormat.parse("2020/12/12 14:20:12"));
        reservationService.addReservation(17,3,2251422, dateFormat.parse("2020/12/12 15:20:12"));
        reservationService.addReservation(18,5,4442321, dateFormat.parse("2020/12/12 15:50:12"));


        console.startConsole();


    }
}
