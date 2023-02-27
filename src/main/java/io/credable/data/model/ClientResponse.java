package io.credable.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientResponse {
    private Integer id;
    private String url;
    private String name;
    private String username;
    private String password;
    private String token;
}
