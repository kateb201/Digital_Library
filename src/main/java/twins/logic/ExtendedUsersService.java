package twins.logic;

import java.util.List;

import twins.boundaries.UserBoundary;

public interface ExtendedUsersService extends UsersService{

	List<UserBoundary> getAllMessagesByTheName(String space, String email, int size, int page);

}
