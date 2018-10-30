package mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

public interface IMapper<T> {
	
    /**
     * Select object by primary key
     * @param entity Entity class
     * @param primaryKey Primary key of the object
     * @return Requested object
     */
	T get(final Class<T> entity, final Serializable primaryKey);
	
	/**
	 * Select objects
	 * @param query SQL query string
	 * @param parameters Parameters of the query
	 * @return Collection of requested objects
	 */   
	Collection<T> select(String query, Map<String, T> parameters);

	/**
	 * Saving entity information to database
	 * Update current value if it can't be inserted twice
	 * 
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */	
	void save(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException;
	
	/**
     * Insert entity into database
     *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	void insert(T entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException;
	
	/**
	 * Update existing entity
	 * Merge the state of the current entity instance with the corresponding state in database
	 *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	void update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException;
	
	/**
	 * Delete existing entity
	 *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	void delete(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException;

}
