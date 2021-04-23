package twins.data;

import org.springframework.data.repository.CrudRepository;

public interface ServiceHandler extends CrudRepository<UserEntity, String> {
}
