package Tests;

import Domain.Movie;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Repository.RepositoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    @Test
    void create() throws Exception {
        //setup
        IRepository<Movie> movieIRepository=new InMemoryRepository<>();

        Movie movie1 = new Movie(1, "The Night Before Christmas",2002,25,true);
        Movie movie2 = new Movie(2, "Cats and Dogs",2017,25,true);
        Movie movie3 = new Movie(2, "Beautiful Stranger at Night",2009,25,true);
        Movie movie4 = new Movie(4, "Everything about you",2020,25,true);
        //act
        movieIRepository.create(movie1);
        //assert
        assertEquals(1, movieIRepository.read().size());

        movieIRepository.create(movie2);
        assertEquals(2, movieIRepository.read().size());
        try{
        movieIRepository.create(movie3);
        fail("The method adds a movie with existing ID without throwing exception");
        }catch (Exception exception){
            assertEquals(2, movieIRepository.read().size(), "Movie with existing ID added to the repository");
        }

    }

    @Test
    void update() {
    }

    @Test
    void readOne() {
    }

    @Test
    void read() {
    }

    @Test
    void delete() {
    }
}