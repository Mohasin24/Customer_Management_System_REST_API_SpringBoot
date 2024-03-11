package com.customer_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Customer_Details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    @NotNull(message = "Please provide first name")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Please provide last name")
    private String lastName;

    @Column(name = "email", unique = true)
    @Email(message = "In-valid email format")
    @NotNull(message = "Email must be provided")
    private String email;

    @Column(name = "mob. No.")
    @Size(min = 10,max = 10)
    @NotNull(message = "Mobile number must be provided")
    private String mobileNo;

}
