package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields
public class CustomerDTO {

    @JsonProperty("customer_id")
    private int id;
    
    @JsonProperty("customer_name")
    private String name;
    
    @JsonProperty("customer_email")
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Only deserialize; don't serialize
    private String password;
}

