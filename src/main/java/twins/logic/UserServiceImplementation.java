package twins.logic;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twins.boundaries.UserBoundary;
import twins.data.ServiceHandler;
import twins.data.UserEntity;

import java.util.List;

@Service
public class UserServiceImplementation implements UsersService {

    private ServiceHandler serviceHandler;
    private ObjectMapper jackson;

    @Autowired
    public UserServiceImplementation(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
        this.jackson = new ObjectMapper();
    }

    @Override
    @Transactional
    public UserBoundary createUser(UserBoundary user) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserBoundary login(String userSpace, String userEmail) {
        return null;
    }

    @Override
    @Transactional
    public UserBoundary updateUser(String userSpace, String userEmail, UserBoundary update) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBoundary> getAllUsers(String adminSpace, String adminEmail) {
        return null;
    }

    @Override
    @Transactional
    public void deleteAllUsers(String adminSpace, String adminEmail) {

    }

    private UserEntity convertToEntity(@NotNull UserBoundary boundary) {
        return new UserEntity(boundary.getUsername(), boundary.getEmail(), boundary.getRole(), boundary.getAvatar());
    }


    private UserBoundary convertToBoundary(@NotNull UserEntity entity) {
        return new UserBoundary(entity.getUsername(), entity.getEmail(), entity.getRole(), entity.getAvatar());
    }
}
