package twins.data;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHandler extends MongoRepository<ItemEntity, String>{

	public List<ItemEntity> findAllBySpaceAndEmail(
			@Param("space")String space,
			@Param("email") String email, 
			Pageable of);
	
	public List<ItemEntity> findByName(
			@Param("name")String name,
			Pageable of);
}
