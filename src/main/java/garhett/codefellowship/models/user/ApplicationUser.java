package garhett.codefellowship.models.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "followTable",
            joinColumns = {@JoinColumn(name = "following")},
            inverseJoinColumns = {@JoinColumn(name = "follower")}
            )

            public Set<ApplicationUser> userFollowing = new HashSet<>();

    @ManyToMany(mappedBy = "userFollowing")
            public Set<ApplicationUser> userFollowers = new HashSet<>();

    String username;
    String password;
    String firstName;
    String lastName;
    long socialSecurity;
    java.sql.Date dob;
    String bio;

    public ApplicationUser() {};

    public ApplicationUser(String username, String password, String firstName, String lastName, long socialSecurity, java.sql.Date dob, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurity = socialSecurity;
        this.dob = dob;
        this.bio = bio;
    }

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    public List<MessagePost> message = new ArrayList<>();
    public Date timeStamp = new Date(System.currentTimeMillis());


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public long getSocialSecurity() { return socialSecurity; }

    public java.sql.Date getDob() { return dob; }

    public String getBio() { return bio; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurity=" + socialSecurity +
                ", dob=" + dob +
                ", bio='" + bio + '\'' +
                '}';
    }
}
