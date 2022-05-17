package entity;

import java.util.Objects;

public class User {
    public String id;
    private String name;
    private String email;
    private String contactNum;

    public User(final String id) {
        this.id = id;
    }

    public User(
            final String id,
            final String name,
            final String email,
            final String contactNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    public String getUserId() {
        return id;
    }

    public String getUserName() {
        return name;
    }

    public String getUserEmail() {
        return email;
    }

    public String getUserContactNum() {
        return contactNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || o.getClass() != getClass()) return false;
        User that = (User) o;
        return Objects.equals(that.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
