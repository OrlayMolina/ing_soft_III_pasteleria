package co.edu.uniquindio.ing.soft.pasteleria.domain.model;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private TypeDocument typeDocument;
    private String phone;
    private Long position;
    private Float salary;
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(Long id, TypeDocument typeDocument, String phone, Long position, Float salary,
                String firstName, String secondName, String lastName, String secondLastName,
                String email, String password, Status status, LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.id = id;
        this.typeDocument = typeDocument;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
