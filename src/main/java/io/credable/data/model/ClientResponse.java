package io.credable.data.model;

public class ClientResponse {
    
    private Integer id;
    private String url;
    private String name;
    private String username;
    private String password;
    private String token;

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //constructors
    public ClientResponse() {
    }
    
    public ClientResponse(Integer id, 
                          String url, 
                          String name, 
                          String username, 
                          String password, 
                          String token) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.username = username;
        this.password = password;
        this.token = token;
    }
}
