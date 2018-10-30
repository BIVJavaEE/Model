package mapping;

import java.util.Map;

public interface IMapperFactory<T>{
	
	public Mapper<T> createMapper(String database, Map<String, String> properties) throws MapperFactoryException;
	
	public Mapper<T> createMapper(String database) throws MapperFactoryException;
}
