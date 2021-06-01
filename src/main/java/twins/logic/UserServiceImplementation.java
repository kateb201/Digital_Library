package twins.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twins.boundaries.UserBoundary;
import twins.boundaries.UserId;
import twins.data.UserHandler;
import twins.data.UserEntity;
import twins.data.UserRole;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImplementation implements UsersService {

    private UserHandler serviceHandler;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserServiceImplementation(UserHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    @Transactional
    public UserBoundary createUser(UserBoundary user) {
        if (user == null) {
            throw new RuntimeException("User must not be null");
        }
        if (assertUser(user)) {
            UserEntity userEntity = convertToEntity(user);
            if (!checkDup(userEntity)) {
                userEntity.setCurrentTimestamp(new Date());
                serviceHandler.save(userEntity);
                UserBoundary userBoundary = convertToBoundary(userEntity);
                return userBoundary;
            }
        }
        throw new RuntimeException("Cannot create user, check all attributes are correct");
    }

    private boolean checkDup(UserEntity userEntity) {
        Optional<UserEntity> existing = serviceHandler.findById(userEntity.getUsername());
        return existing.isPresent();
    }

    private boolean assertUser(UserBoundary user) {
        if (user.getUsername().equals("") || user.getUsername() == null) {
            throw new RuntimeException("User name not valid");
        }
        if (user.getUserId().getEmail() == null || !assertEmail(user.getUserId().getEmail())) {
            throw new RuntimeException("Email not valid");
        }
        if (user.getAvatar().equals("") || user.getAvatar() == null) {
            throw new RuntimeException("Avatar not valid");
        }
        if (user.getRole() == null || !isInEnum(user.getRole())) {
            throw new RuntimeException("Role not valid");
        }
        return true;
    }

    private <E extends Enum<E>> boolean isInEnum(String value) {
        for (UserRole e : UserRole.class.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean assertEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    @Override
    @Transactional(readOnly = true)
    public UserBoundary login(String userSpace, String userEmail) {
        Optional<UserEntity> existing = serviceHandler.findById(userEmail);
        if (existing.isPresent()) {
            UserEntity entity = existing.get();
            return this.convertToBoundary(entity);
        } else {
            throw new RuntimeException("message could not be found");
        }
    }

    @Override
    @Transactional
    public UserBoundary updateUser(String userSpace, String userEmail, UserBoundary update) {
        Optional<UserEntity> existing = this.serviceHandler.findById(userEmail);
        if (existing.isPresent()) {
            update.getUserId().setEmail(userEmail);
            UserEntity updatedEntity = this.convertToEntity(update);
            updatedEntity.setCurrentTimestamp(existing.get().getCurrentTimestamp());
            this.serviceHandler.save(updatedEntity);
            return convertToBoundary(updatedEntity);
        } else {
            throw new RuntimeException("User could not be found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBoundary> getAllUsers(String adminSpace, String adminEmail) {
    	Optional<UserEntity> user = serviceHandler.findById(adminEmail);
		if (!user.isPresent() || user.get().getRole() != UserRole.ADMIN.toString()) {
			throw new UncheckedIOException("User " + adminEmail + " is not premitted", null);
		}
        Iterable<UserEntity> allEntities = this.serviceHandler.findAll();
        List<UserBoundary> rv = new ArrayList<>();
        for (UserEntity entity : allEntities) {
            UserBoundary boundary = this.convertToBoundary(entity);
            rv.add(boundary);
        }
        return rv;
    }

    @Override
    @Transactional
    public void deleteAllUsers(String adminSpace, String adminEmail) {
    	Optional<UserEntity> user = serviceHandler.findById(adminEmail);
		if (!user.isPresent() || user.get().getRole() != UserRole.ADMIN.toString()) {
			throw new UncheckedIOException("User " + adminEmail + " is not premitted", null);
		}
        serviceHandler.deleteAll();
    }

    private UserEntity convertToEntity(UserBoundary boundary) {
        return new UserEntity(boundary.getUsername(), boundary.getUserId().getEmail(), boundary.getRole(), boundary.getAvatar());
    }


    private UserBoundary convertToBoundary(UserEntity entity) {
        return new UserBoundary(new UserId(entity.getUserSpace(), entity.getEmail()), entity.getRole(), entity.getUsername(), entity.getAvatar());
    }
}
