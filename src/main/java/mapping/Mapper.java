package mapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import entity.IEntity;

/**
 * Entity - Database object mapping class
 */
public class Mapper<T extends IEntity> implements IMapper<T>{

	private EntityManagerFactory factory;
	
	public Mapper(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
   /**
    * Select object by primary key
    * @param entity Entity class
    * @param primaryKey Primary key of the object
    * @return Requested object
    */
	public T get(final Class<T> entity, final int primaryKey) {
        EntityManager manager = this.factory.createEntityManager();
		T item = null;
        try {
			manager.getTransaction().begin();
			item = manager.find(entity, primaryKey);
			manager.getTransaction().commit();			
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			manager.getTransaction().rollback();
			throw e;
		}catch(IllegalStateException e) {
			System.out.println("Can't get data, can't use transaction on JTA entity manager: " + e.getMessage());
			throw e;
		}finally {
			manager.close();
		}
        return item;
    }
	
   /**
    * Select objects
    * @param query SQL query string
    * @param parameters Parameters of the query
    * @return Collection of requested objects
    */
	@SuppressWarnings("unchecked")
    public Collection<T> query(String query, Map<String, T> parameters) {
		EntityManager manager = this.factory.createEntityManager();
		List<T> items = null;
        try {
			manager.getTransaction().begin();
			Query querySQL = manager.createQuery(query);
            if(parameters != null && !parameters.isEmpty()) {
                for(Map.Entry<String, T> entry : parameters.entrySet()) {
                    querySQL.setParameter(entry.getKey(), entry.getValue());
                }
            }
            items = querySQL.getResultList();
			manager.getTransaction().commit();			
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			manager.getTransaction().rollback();
			throw e;
		}catch(IllegalStateException e) {
			System.out.println("Can't get data, can't use transaction on JTA entity manager: " + e.getMessage());
			throw e;
		}finally {
			manager.close();
		}
        return items;    
	}
	
	/**
	 * Saving entity information to database
	 * Update current value if it can't be inserted twice
	 * 
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	public void save(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException{
		try {
			// If value already exists update it (exception is more efficient than queries to apply criteria)
			try {
				insert(entity);
			}catch (EntityExistsException e) {
				update(entity);
			}
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			System.out.println("Can't save entity : " + e.getMessage());
			throw e;
		}catch(IllegalStateException e) {
			throw e;
		}
	}
	
    /**
     * Insert entity into database
     *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	public void insert(T entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException {
		EntityManager manager = this.factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();			
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			manager.getTransaction().rollback();
			throw e;
		}catch(IllegalStateException e) {
			System.out.println("Can't insert data can't use transaction on JTA entity manager: " + e.getMessage());
			throw e;
		}finally {
			manager.close();
		}
	}
	
	/**
	 * Update existing entity
	 * Merge the state of the current entity instance with the corresponding state in database
	 *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	public void update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {	
		EntityManager manager = this.factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			entity = manager.merge(entity);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			manager.getTransaction().rollback();
			throw e;
		}catch(IllegalStateException e) {
			System.out.println("Can't update data can't use transaction on JTA entity manager: " + e.getMessage());
			throw e;
			
		}finally {
			manager.close();
		}
		
	}
	
	/**
	 * Delete existing entity
	 *  
	 * @param entity Object to persist
	 * @throws IllegalArgumentException
	 * @throws TransactionRequiredException
	 */
	public void delete(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
		EntityManager manager = this.factory.createEntityManager();
		try {			
			manager.getTransaction().begin();
			manager.remove(entity);
			manager.getTransaction().commit();
			entity = null;
		}catch(IllegalArgumentException | TransactionRequiredException e) {
			manager.getTransaction().rollback();
			throw e;
		}catch(IllegalStateException e) {
			System.out.println("Can't delete data can't use transaction on JTA entity manager: " + e.getMessage());
			throw e;
		}finally {
			manager.close();
		}
	}
    
}
