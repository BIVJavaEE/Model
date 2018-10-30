package mapping;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MapperFactory<T> implements IMapperFactory<T>{

	public Mapper<T> createMapper(String database, Map<String, String> properties) throws MapperFactoryException{
		Mapper<T> mapper = null;
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(database, properties);
			mapper = new Mapper<T>(factory);
		}catch(Exception e) {
			throw new MapperFactoryException("Connection to database failed : "  + e.getMessage());
		}
		return mapper;
	}
	
	public Mapper<T> createMapper(String database) throws MapperFactoryException{
		return createMapper(database, null);
	}
	
}
