package twins.data;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserHandler extends MongoRepository<UserEntity, String> {


	List<UserEntity> findAllUsersByUserSpace(@Param("space")String space, 
			Pageable of);
}
