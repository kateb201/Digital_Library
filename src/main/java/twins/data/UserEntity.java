package twins.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

/*
       USER_TABLE

    EMAIL <PK>| USERNAME      | ROLE        | TIMESTAMP         | AVATAR              | SPACE
===================================================================================================
VARCHAR(255) | VARCHAR(255)  | VARCHAR(255)   | TIMESTAMP         | VARCHAR(255)     | VARCHAR(255)
 */

@Document(collection = "User")
public class UserEntity {
    private String username;
    @Id
    private String email;
    private String role;
    private String avatar;
    private Date currentTimestamp;
    private String userSpace;

    public UserEntity() {
    }

    public UserEntity(String username, String email, String role, String avatar) {
        this.email = email;
        this.role = role;
        this.username = username;
        this.avatar = avatar;
        this.setUserSpace("2021b.katya.boyko") ;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Field(name = "TIMESTAMP")
    public Date getCurrentTimestamp() {
        return currentTimestamp;
    }


    public void setCurrentTimestamp(Date currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

	public String getUserSpace() {
		return userSpace;
	}

	public void setUserSpace(String userSpace) {
		this.userSpace = userSpace;
	}
}
