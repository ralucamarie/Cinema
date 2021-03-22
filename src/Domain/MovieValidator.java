package Domain;

public class MovieValidator {
    /**
     *
     * @param movie
     * @throws Exception
     */
    public void validate (Movie movie) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        if (movie.getTicketPrice()==0) stringBuilder.append("Pretul biletului nu poate fi 0!");


        if (stringBuilder.length() > 0) {
            throw new Exception(stringBuilder.toString());
        }
    }
}
