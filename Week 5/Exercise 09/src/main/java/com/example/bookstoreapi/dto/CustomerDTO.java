package com.example.bookstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends RepresentationModel<CustomerDTO>{

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
