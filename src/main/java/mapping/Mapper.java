package mapping;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TransactionRequiredException;

/**
 * Entity - Database object mapping class
 */
public class Mapper<T> {

	private EntityManagerFactory factory;
	
	public Mapper(EntityManagerFactory factory) {
		this.factory = factory;
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
