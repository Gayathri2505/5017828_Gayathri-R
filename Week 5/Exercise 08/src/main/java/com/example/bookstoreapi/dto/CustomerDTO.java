package com.example.bookstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	@Min(value = 1, message = "Customer ID must be a positive number")
	@NotNull(message = "Customer ID must not be null")
	private Long id;
        
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Email
    private String email;

   /* @NotNull
    @Size(min = 10, max = 15)
    private String phoneNumber;*/
}
