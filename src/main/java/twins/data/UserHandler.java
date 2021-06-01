package twins.data;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserHandler extends PagingAndSortingRepository<UserEntity, String> {

	List<UserEntity> findAllByUserSpace(
			@Param("space")String space, 
			Pageable of);
}
