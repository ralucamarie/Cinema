package Service;

import Domain.Movie;
import Domain.Reservation;
import Domain.ReservationValidator;
import Repository.IRepository;
import Repository.RepositoryException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ReservationService {
    private IRepository<Movie> movieRepository;
    private IRepository<Reservation> reservationRepository;
    private ReservationValidator reservationValidator;

    /**
     * Constructor
      * @param reservationRepository
     * @param movieRepository
     * @param reservationValidator
     */
    public ReservationService(IRepository<Reservation> reservationRepository, IRepository<Movie> movieRepository, ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.movieRepository = movieRepository;
        this.reservationValidator = reservationValidator;
    }

    /**
     * Creates a reservation in the repository.
     * @param id Reervation ID
     * @param idMovie Movie Id the reservation is beign made for.
     * @param customerCard Customer Card number.
     * @param dateTime Date and time of the reservations.
     * @throws RepositoryException if the reservation doesn't pass the validation.
     */
    public void addReservation (int id, int idMovie, int customerCard, LocalDateTime dateTime) throws Exception {
        Reservation reservation = new Reservation(id,idMovie,customerCard,dateTime);
        this.reservationValidator.validate(reservation,this.movieRepository);
        this.reservationRepository.create(reservation);
    }

    /**
     * Searches and returns a reservation based on the searched ID.
     * @param id Id to serach for
     * @return Return reservation detalis of the reservation with serached ID.
     * @throws RepositoryException if the id was not found.
     */
    public Reservation returnOne(int id) throws Exception {
        return this.reservationRepository.readOne(id);
    }

    /**
     * Overwrites an existing Reservation details.
     * @param id of the reservation to be updated.
     * @param idMovie Id of the movie the reservation was made for.
     * @param customerCard Customer Card number.
     * @param dateTime of the reservation.
     * @throws RepositoryException if the id is not on the repository list.
     */
    public void updateReservation (int id, int idMovie, int customerCard, LocalDateTime dateTime) throws Exception {
        Reservation reservation = new Reservation(id,idMovie,customerCard,dateTime);
        this.reservationValidator.validate(reservation,this.movieRepository);
        this.reservationRepository.update(reservation);
    }

    /**
     * Removes a reservation from the repository.
     * @param id of the reservation to be removed
     * @param idMovie Id of the movie the reservation was made for.
     * @param customerCard Customer Card number.
     * @param dateTime of the reservation.
     * @throws Exception if the reservation is not on the list.
     */
    public void deleteReservation (int id, int idMovie, int customerCard, LocalDateTime dateTime) throws Exception {
        Reservation reservation = new Reservation(id,idMovie,customerCard,dateTime);
        this.reservationRepository.delete(reservation.getId());
    }

    /**
     * Reads the list of reservations in the repository.
     * @return the repository list of reservations.
     */
    public List<Reservation> readAll(){
        return this.reservationRepository.read();
    }

    public void searchEntityByHourInterval(int hour1, int hour2) {
        for (Reservation reservation : this.reservationRepository.read()){
            if ((reservation.getDateTime().getHour()>hour1)&&(reservation.getDateTime().getHour()<hour2)){
                System.out.println(reservation.toString());
            }
        }
    }
    public void MoviesByReservations() throws Exception {
        Map<Movie, Integer> moviesByReservations = new HashMap<>();
        for (Reservation reservation : this.reservationRepository.read()) {
            if (!moviesByReservations.containsKey(this.movieRepository.readOne(reservation.getIdmovie()))) {
                //Movie movieToAdd =
                moviesByReservations.put(this.movieRepository.readOne(reservation.getIdmovie()), 1);
            } else {
                int noOfReservations = moviesByReservations.get(this.movieRepository.readOne(reservation.getIdmovie())) + 1;
                moviesByReservations.put(this.movieRepository.readOne(reservation.getIdmovie()), noOfReservations);
            }
        }
        Set<Map.Entry<Movie, Integer>> entrySet = moviesByReservations.entrySet();
        List<Map.Entry<Movie,Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Movie, Integer>>() {
            @Override
            public int compare(Map.Entry<Movie, Integer> o1, Map.Entry<Movie, Integer> o2) {
                return  o2.getValue().compareTo(o1.getValue());
            }
        });
        list.forEach(s->{
            System.out.println(s.getKey()+"\t"+s.getValue());
        });
    }
        public void CardsByReservations() throws Exception {
            Map <Integer,Integer> cardsByReservations = new HashMap<>();
            for (Reservation reservation : this.reservationRepository.read()){
                if (!cardsByReservations.containsKey(reservation.getCustomerCard())){
                    //Movie movieToAdd =
                    cardsByReservations.put(reservation.getCustomerCard(),1);
                }else {
                    int noOfReservations = cardsByReservations.get(reservation.getCustomerCard())+1;
                    cardsByReservations.put(reservation.getCustomerCard(),noOfReservations);
                }
            }
        Set <Map.Entry<Integer,Integer>> entrySet = cardsByReservations.entrySet();
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return  o2.getValue().compareTo(o1.getValue());
            }
        });
        list.forEach(s->{
            System.out.println(s.getKey()+"\t"+s.getValue());
        });

    }


    public void deleteReservationInDateInterval(LocalDate dateTime1, LocalDate dateTime2) throws Exception {
        for (Reservation reservation : this.reservationRepository.read()){

            if (reservation.getDateTime().toLocalDate().isAfter(dateTime1)&&reservation.getDateTime().toLocalDate().isBefore(dateTime2)){
                this.reservationRepository.delete(reservation.getId());
            }
        }
    }
}
