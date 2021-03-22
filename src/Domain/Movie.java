package Domain;

public class Movie extends Entity{
    //CRUD film: id, titlu, an apariție,
    // preț bilet, în program. Prețul să fie strict pozitiv.

    private String title;
    private int year;
    private Integer ticketPrice;
    private boolean running;

    public Movie(int id, String title, int year, Integer ticketPrice, boolean running) {
        super(id);
        this.title = title;
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.running=running;
    }

    public int getId() {
        return super.getIdEntity();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public boolean isRunning() {
        return running;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + super.getIdEntity() +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", ticketPrice=" + ticketPrice +
                ", running=" + running +
                '}';
    }
}
