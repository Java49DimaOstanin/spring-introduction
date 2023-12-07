package telran.spring.service;



import java.util.*;

import org.springframework.stereotype.Service;


@Service
public class GreetingsServiceImpl implements GreetingsService {
	Map<Long,String> greetingsMap = new HashMap<>(Map.of(123l,"David",124l,"Sara",125l,"Rivka"));
	
	
	@Override
	public String getGreetings(long id) {
		String name = greetingsMap.getOrDefault(id, "Unknown Guest");
		return "Hello, " + name;
	}


	@Override
	public String addName(IdName idName) {
		String name = greetingsMap.putIfAbsent(idName.id(), idName.name());
		if(name != null) {
			 throw new IllegalStateException(idName.id() +" name with gived id alredy exists");
		}
		return idName.name();
	}


	@Override
	public String deleteName(long id) {
		String name = greetingsMap.remove(id);
		if(name == null) {
			throw new IllegalStateException(id + " not found");
		}
		return null;
	}


	@Override
	public String updateName(IdName idName) {
		if(!greetingsMap.containsKey(idName.id())) {
			throw new IllegalStateException(idName.id() + " not found");
		}
		greetingsMap.put(idName.id(), idName.name() );
		return idName.name();
	}

}

