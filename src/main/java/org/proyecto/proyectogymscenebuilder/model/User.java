package org.proyecto.proyectogymscenebuilder.model;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String username;
    private String password;
}
