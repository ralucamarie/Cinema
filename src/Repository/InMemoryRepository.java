package Repository;

import Domain.Entity;
import Domain.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository <T extends Entity> implements IRepository<T>{
    private Map<Integer,T> storage= new HashMap<>();

    //CRUD - Create, Read, Update, Delete. Prețul să fie strict pozitiv.

    /**Creates an entry to the repository. Adds a Movie.
     *If there already is the movie on the list, throw exception.
     */
    public void create(T entity) throws Exception{
        if (this.storage.containsKey(entity.getIdEntity())) {
            throw new Exception("The movie with this ID is already on the list");
        }
        this.storage.put(entity.getIdEntity(),entity);

    }

    /**
     * Updates a movie that already is in the Repository.
     * @param entity The movie I want to update.
     * @throws Exception if the movie doesn't exist.
     */
    public void update (T entity) throws Exception {
        if (!storage.containsKey(entity.getIdEntity())) {
            throw new Exception ("ID-ul nu este in lista");
        } else {
            storage.put(entity.getIdEntity(),entity);
        }
    }


    /**
     * Searches and returns a movie from the Repository.
     * @param id the id to search the existing movie.
     * @return the corresponding movie to the ID.
     * @throws Exception if the movie doesn't exist in the repository.
     */
    public T readOne(int id) throws Exception{
        if (!this.storage.containsKey(id)) {
            throw new Exception("ID-ul nu exista in lista");
        }
        return this.storage.get(id);
    }

    /**
     *
     * @return
     */
    public List<T> read (){
        return new ArrayList<>(this.storage.values());
    }

    /**
     * Removes an entry from the repository.
     * @param entityId the ID of the movie I want to remove.
     * @throws Exception if the movieID is not on the list.
     */
    public void delete (Integer entityId) throws Exception {
        if (!this.storage.containsKey(entityId)) {
            throw new Exception("ID-ul nu exista in lista");
        }
        this.storage.remove(entityId, storage.get(entityId));
    }
}
