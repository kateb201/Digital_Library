package twins.logic;
import java.util.List;
import twins.boundaries.ItemBoundry;

public interface ExtendedOperationsService extends OperationsService{
	
	public List<ItemBoundry> searchByName(String name, int size, int page);
	
	public List<ItemBoundry> searchBySubject(String subject, int size, int page);

}
