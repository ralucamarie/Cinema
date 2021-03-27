import Domain.MovieValidator;
import Domain.ReservationValidator;
import Repository.InMemoryRepository;
import Service.MovieService;
import Service.ReservationService;
import Service.UndoRedoManager;
import UserInterface.Console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        InMemoryRepository movieRepository = new InMemoryRepository();
        InMemoryRepository reservationRepository = new InMemoryRepository();
        MovieValidator movieValidator = new MovieValidator();
        ReservationValidator reservationValidator = new ReservationValidator();
        UndoRedoManager undoRedoManager=new UndoRedoManager();
        MovieService movieService = new MovieService(movieRepository,movieValidator,reservationRepository,undoRedoManager);
        ReservationService reservationService= new ReservationService(reservationRepository,movieRepository,reservationValidator,undoRedoManager);
        Console console = new Console(movieService,reservationService,undoRedoManager);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        LocalDateTime dateTime1 = LocalDateTime.of(2019,Month.MAY,12,10,20,5);
        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);


        movieService.addMovie(1, "The Night Before Christmas",2002,25,true);
        movieService.addMovie(2, "Cats and Dogs",2017,25,true);
        movieService.addMovie(3, "Beautiful Stranger at Night",2009,25,true);
        movieService.addMovie(4, "Everything about you",2020,25,true);
        movieService.addMovie(5, "Sailing",2014,25,true);
        movieService.addMovie(6, "Good night, I say to you",2014,25,true);

        reservationService.addReservation(11,1,4442321, LocalDateTime.of(2019,5,12,10,20,5));
        reservationService.addReservation(12,3,9965412, LocalDateTime.of(2019,7,22,18,25,30));
        reservationService.addReservation(13,3,2251422, LocalDateTime.of(2020,10,3,5,11,8));
        reservationService.addReservation(14,5,4442321, LocalDateTime.of(2020,6,12,6,20,5));
        reservationService.addReservation(15,6,4442321, LocalDateTime.of(2019,9,20,7,30,12));
        reservationService.addReservation(16,3,9965412, LocalDateTime.of(2021,1,15,9,10,1));
        reservationService.addReservation(17,3,2251422, LocalDateTime.of(2021,2,7,22,20,52));
        reservationService.addReservation(18,5,4442321, LocalDateTime.of(2019,3,11,13,25,35));


        console.startConsole();


    }
}
