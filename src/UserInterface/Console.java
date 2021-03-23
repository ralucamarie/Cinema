package UserInterface;

import Domain.Movie;
import Domain.Reservation;
import Service.MovieService;
import Service.ReservationService;


import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class Console {
    private MovieService movieService;
    private ReservationService reservationService;
    private Scanner scanner = new Scanner(System.in);


    public Console(MovieService movieService, ReservationService reservationService) {
        this.movieService = movieService;
        this.reservationService = reservationService;
    }

    private void menu (){
        System.out.println ("1.Add Movie");
        System.out.println ("2.Update Movie");
        System.out.println ("3.Remove Movie");
        System.out.println ("4.Show all Movies");
        System.out.println ("5.Add Reservation");
        System.out.println ("6.Delete Reservation");
        System.out.println ("7.Show all Reservations");
        System.out.println ("0.Search Movies");
        System.out.println ("a.Search reservations between time interval");
        System.out.println ("b.Display movies by reservation numbers");
        System.out.println ("c.Display cards by reservation numbers");
        System.out.println ("d.Delete reservations in date interval");
        System.out.println ("x.End program");
    }

    public void startConsole() throws Exception {
        while (true){
            this.menu();
            System.out.println("Your option:");
            String option=scanner.next();

            if (option.equals("1")){
                this.handleAddMovie();
            } else if (option.equals("2")){
                this.handleUpdateMovie();
            } else if (option.equals("3")){
                this.handleRemoveMovie();
            }else if (option.equals("4")){
                this.handleShowAllMovies();
            }else if (option.equals("5")){
                this.handleAddReservation();
            }else if (option.equals("6")){
                this.handleRemoveReservation();
            }else if (option.equals("7")){
              this.handleShowAllReservations();
            }else if (option.equals("0")) {
                this.handleSearchMovie();
            } else if (option.equals("a")){
                    this.handleSearchReservationByTime();
            } else if (option.equals("b")){
                this.handleMoviesByReservations();
            } else if (option.equals("c")){
                this.handleCardsByReservations();
            } else if (option.equals("d")){
                this.handleDeleteReservationsFromDayInterval();
            }else if (option.equals("x")){
                System.exit(0);
            }
            
        }
    }

    private void handleDeleteReservationsFromDayInterval() throws Exception {
        System.out.println("Delete Reservations from (dd-MMM-yyyy): ");
       Scanner scan = new Scanner(System.in);
        LocalDate dateTime1;
        String date = scan.next();
        dateTime1 = dateInput(date);

        System.out.println("Second hour interval (dd-MMM-yyyy): ");
        LocalDate dateTime2;
        date = scan.next();
        dateTime2 = dateInput(date);


        this.reservationService.deleteReservationInDateInterval(dateTime1,dateTime2);
    }
    public static LocalDate dateInput(String userInput) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);


        System.out.println(date);
        return date ;
    }

    private void handleCardsByReservations() throws Exception {
        this.reservationService.CardsByReservations();
    }

    /**  private void handleMoviesByReservations() throws Exception {

        Map<Movie,Integer> movies = this.reservationService.MoviesByReservations();
        Stream<Map.Entry<Movie,Integer>> sortedMap = movies.entrySet().stream().sorted(Map.Entry.comparingByValue());
                sortedMap.forEach(s ->System.out.println(s));

        /**for (Map.Entry<Movie,Integer> entry : movies.entrySet()){
            System.out.println(entry.toString());;
        }
    }**/
  private void handleMoviesByReservations() throws Exception {
    this.reservationService.MoviesByReservations();

  }


      /**for (Map.Entry<Movie,Integer> entry : movies.entrySet()){
       System.out.println(entry.toString());;
       }**/


    private void handleSearchReservationByTime() {

        System.out.println("First Hour interval: ");
        Integer hour1=scanner.nextInt();
        System.out.println("Second hour interval: ");
        Integer hour2=scanner.nextInt();
        this.reservationService.searchEntityByHourInterval(hour1,hour2);


    }

    private void handleSearchMovie() {
        System.out.println("Enter Search Key:");
        String searchedName=scanner.next();
        this.movieService.searchEntity(searchedName);

    }

    private void handleShowAllReservations() {
        for (Reservation reservation : this.reservationService.readAll()){
            System.out.println(reservation.toString());
        };
    }

    private void handleRemoveReservation() throws Exception {
        System.out.println("Id of the reservation you want to remove:");
        int id = scanner.nextInt();
        Reservation reservation = this.reservationService.returnOne(id);
        this.reservationService.deleteReservation(reservation.getId(), reservation.getIdmovie(),reservation.getCustomerCard(),reservation.getDateTime());
        System.out.println("Reservation deleted!");
    }

    private void handleShowAllMovies(){
        //List<Movie> all = this.movieService.readAll();
        for (Movie movie : this.movieService.readAll()){
            System.out.println(movie.toString());
        }
    }


    private void handleAddReservation() {
        //int id, int idMovie, int customerCard, String dateTime
        try{
            System.out.println ("Reservation ID:");
            int id = scanner.nextInt();
            System.out.println ("Reservation Movie ID");
            int movieId = scanner.nextInt();
            System.out.println ("Customer Card");
            int card = scanner.nextInt();
            LocalDateTime date = LocalDateTime.now();

            this.reservationService.addReservation(id,movieId,card,date);
        } catch (Exception exception){
            System.out.println("Au aparul erorile:");
            System.out.println(exception.getMessage());
        }
    }
    
    private void handleRemoveMovie() throws Exception {
        System.out.println ("Id of the movie you want to remove:");
        int id = scanner.nextInt();
        Movie movie = this.movieService.returnMovie(id);
        this.movieService.deleteMovie(movie.getId(),movie.getTitle(), movie.getYear(), movie.getTicketPrice(),movie.isRunning());
        System.out.printf("Movie Deleted!");
    }
    
    private void handleUpdateMovie() throws Exception {
        System.out.println ("Id of the movie you want to upate:");
        int id = scanner.nextInt();
        Movie movie = this.movieService.returnMovie(id);
        while (true) {
            this.updateMenu();
            System.out.println("Your option:");
            String option=scanner.next();

            if (option.equals("1")){
                System.out.println("Enter Movie Title");
                scanner.nextLine();
                String title=scanner.nextLine();
                movie.setTitle(title);
            }else if (option.equals("2")){
                System.out.println("Enter Movie Year");
                int year=scanner.nextInt();
                movie.setYear(year);
            }else if (option.equals("3")){
                System.out.println("Enter Movie Ticket Price");
                int price=scanner.nextInt();
                movie.setTicketPrice(price);
            }else if (option.equals("4")){
                System.out.println("Is movie still running? True/False");
                String running=scanner.next();
                movie.setRunning(Boolean.parseBoolean(running));
            }else if (option.equals("x")){
                break;
            }

        }
    }
    private void updateMenu(){
        System.out.println ("   What do you want to update?");
        System.out.println ("       1.Movie Title");
        System.out.println ("       2.Movie Year");
        System.out.println ("       3.Movie Ticket Price");
        System.out.println ("       4.Movie running? true/false");
        System.out.println ("       x.Exit Update");
    }

    private void handleAddMovie() throws Exception {
        try{//int id, String title, int year, Integer ticketPrice, boolean running
            System.out.println("Enter Movie ID: ");
            int movieId=scanner.nextInt();
            System.out.println("Enter Movie Title: ");
            scanner.nextLine();
            String title=scanner.nextLine();
            System.out.println("Enter Movie Year: ");
            int year=scanner.nextInt();
            System.out.println("Enter ticketPrice: ");
            int ticketPrice=scanner.nextInt();
            System.out.println("Running? true/false: ");
            boolean running= Boolean.parseBoolean(scanner.next());

            this.movieService.addMovie(movieId,title,year,ticketPrice,running);
            System.out.println("Film adaugat cu succes.");

        } catch (Exception exception) {
            System.out.println("Au aparut erorile:");
            System.out.println(exception.getMessage());
        }


    }
}
