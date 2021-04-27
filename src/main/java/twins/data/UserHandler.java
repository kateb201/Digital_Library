package twins.data;

import org.springframework.data.repository.CrudRepository;

public interface UserHandler extends CrudRepository<UserEntity, String> {
}
