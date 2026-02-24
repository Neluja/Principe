package model;

public class Admin {

    private String adminId;
    private String username;
    private String password;
    private String name;
    private String role;
    private byte[] image;

    // Getters and Setters

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}