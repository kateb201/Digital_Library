package twins.logic;

import java.util.List;

import twins.boundaries.UserBoundary;

public interface ExtendedUsersService extends UsersService{

	List<UserBoundary> getAllUsersByTheUserSpace(String userSpace, String userEmail, int size, int page);

}
