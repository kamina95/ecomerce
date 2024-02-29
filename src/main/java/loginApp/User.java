package loginApp;

import user.product.UserProduct;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

//@NotNull: Checks that the annotated field is not null.
//@NotEmpty: Checks that the field is not null and its size/length is greater than zero.
//@Size(min=, max=): Checks that the field's size/length is within the specified bounds.
//@Min(value) and @Max(value): Validate that the field's value is within a certain range.
//@Email: Validates that the field is a valid email address.
//@Pattern(regexp=): Ensures the field matches a specified regular expression.

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="name", nullable = false)
    String userName;
    @Column(name="pass", nullable = false)
    String userPass;
    @Column(name="address")
    String userAddress;
    @Column(name="email_address")
    @Email
    String userEmailAddress;
    @Column(name="role", nullable = false)
    String userRole;
    @OneToMany(mappedBy = "user")
    private Set<UserProduct> userProducts = new HashSet<>();


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public String getUserRole() {
        return userRole;
    }
}
