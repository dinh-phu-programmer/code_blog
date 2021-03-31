package com.dinhphu.blog.model.dto;

import com.dinhphu.blog.model.root.RootClass;
import com.dinhphu.blog.validation.EmailConstraint;
import com.dinhphu.blog.validation.FieldPasswordMatch;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@FieldPasswordMatch.List(
        {
                @FieldPasswordMatch(
                        field = "password",
                        fieldMatch = "confirmPassword",
                        message="{password.not.match}"
                )
        }
)
public class UserDTO extends RootClass {

    private Long id;

    @Length(min = 2,max = 10,message="{name.not.null}")
//    @NotNull(message="{name.not.null}")
//    @NotEmpty(message = "{name.not.empty}")
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    @NotNull(message="{email.not.null}")
    @EmailConstraint(message="{email.invalid}")
    private String email;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date joinDate;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String username, String password, String email, String profileImageUrl, Date lastLoginDate, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.joinDate = joinDate;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    public UserDTO(Long id, String firstName, String lastName, String username, String password, String email, String profileImageUrl, Date lastLoginDate, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.joinDate = joinDate;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    public UserDTO(@Length(min = 2, max = 10, message = "{name.not.null}") String firstName, String lastName, String username, String password, String confirmPassword, @NotNull(message = "{email.not.null}") String email, String profileImageUrl, Date lastLoginDate, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.joinDate = joinDate;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", joinDate=" + joinDate +
                ", role='" + role + '\'' +
                ", authorities=" + Arrays.toString(authorities) +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return isActive == userDTO.isActive &&
                isNotLocked == userDTO.isNotLocked &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(profileImageUrl, userDTO.profileImageUrl) &&
                Objects.equals(lastLoginDate, userDTO.lastLoginDate) &&
                Objects.equals(joinDate, userDTO.joinDate) &&
                Objects.equals(role, userDTO.role) &&
                Arrays.equals(authorities, userDTO.authorities);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, username, password, email, profileImageUrl, lastLoginDate, joinDate, role, isActive, isNotLocked);
        result = 31 * result + Arrays.hashCode(authorities);
        return result;
    }
}
