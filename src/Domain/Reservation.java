package Domain;

import java.util.Date;

public class Reservation extends Entity{
    //CRUD rezervare: id, id_film (trebuie să existe), nr. card client (întreg), data și ora. Rezervarea se
    //poate face doar dacă filmul este încă în program.
    private int idMovie;
    private int customerCard;
    private Date dateTime;

    public Reservation(int id, int idMovie, int customerCard, Date dateTime) {
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

    public Date getDateTime() {
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
