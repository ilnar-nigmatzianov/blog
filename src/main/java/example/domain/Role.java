package example.domain;

public enum Role {
    USER, ADMIN;

    public String getAuthority() {
        return name();
    }
}
