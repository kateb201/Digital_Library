package twins.data;


import javax.persistence.*;
import java.util.Date;

/*
       USER_TABLE

USERNAME <PK>| EMAIL         | ROLE            | TIMESTAMP | AVATAR
===============================================================================
VARCHAR(255) | VARCHAR(255)  | VARCHAR(255)   | TIMESTAMP         | VARCHAR(255)
 */
@Entity
@Table(name = "USER_TABLE")
public class UserEntity {
    private String username;
    private String email;
    private String role;
    private String avatar;
    private Date currentTimestamp;

    public UserEntity() {
    }

    public UserEntity(String username, String email, String role, String avatar) {
        this.email = email;
        this.role = role;
        this.username = username;
        this.avatar = avatar;
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
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
}
