package com.teste.picpay.domain.user;

import com.teste.picpay.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userDTO) {
        this.balance = userDTO.balance();
        this.document= userDTO.document();
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.userType = userDTO.userType();
    }



}
