package twins.data;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


public interface ItemHandler extends PagingAndSortingRepository<ItemEntity, String>{

	public List<ItemEntity> findAllBySpaceAndEmail(
			@Param("space")String space,
			@Param("email") String email, 
			Pageable of);
	
	public List<ItemEntity> findByName(
			@Param("name")String name,
			Pageable of);
}
