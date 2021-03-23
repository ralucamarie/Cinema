package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Reservation extends Entity{
    //CRUD rezervare: id, id_film (trebuie să existe), nr. card client (întreg), data și ora. Rezervarea se
    //poate face doar dacă filmul este încă în program.
    private int idMovie;
    private int customerCard;
    private LocalDateTime dateTime;

    public Reservation(int id, int idMovie, int customerCard, LocalDateTime dateTime) {
        super(id);
        this.idMovie = idMovie;
        this.customerCard = customerCard;
        this.dateTime = dateTime;
    }

    public int getId() {
        return super.getIdEntity();
    }

    public int getIdmovie() {
        return idMovie;
    }

    public int getCustomerCard() {
        return customerCard;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }



    @Override
    public String toString() {
        return "rezervare{" +
                "id=" + super.getIdEntity() +
                ", id_movie=" + idMovie +
                ", customerCard=" + customerCard +
                ", dateTime=" + dateTime +
                '}';
    }
}
