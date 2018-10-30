package mapping;

import java.util.Map;

import entity.IEntity;

public interface IMapperFactory<T extends IEntity>{
	
	public Mapper<T> createMapper(String database, Map<String, String> properties) throws MapperFactoryException;
	
	public Mapper<T> createMapper(String database) throws MapperFactoryException;
}
