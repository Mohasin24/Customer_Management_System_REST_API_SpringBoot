package com.customer_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Customer_Details")
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
   

	public Customer() {
		super();
	}

	public Customer(@NotNull(message = "Please provide first name") String firstName,
			@NotNull(message = "Please provide last name") String lastName,
			@Email(message = "In-valid email format") @NotNull(message = "Email must be provided") String email,
			@Size(min = 10, max = 10) @NotNull(message = "Mobile number must be provided") String mobileNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
	}

	public Customer(long id, @NotNull(message = "Please provide first name") String firstName,
			@NotNull(message = "Please provide last name") String lastName,
			@Email(message = "In-valid email format") @NotNull(message = "Email must be provided") String email,
			@Size(min = 10, max = 10) @NotNull(message = "Mobile number must be provided") String mobileNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobileNo=" + mobileNo + "]";
	}
}
