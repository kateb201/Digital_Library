package twins.data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OperationHandler extends MongoRepository<OperationEntity, String>{

}
