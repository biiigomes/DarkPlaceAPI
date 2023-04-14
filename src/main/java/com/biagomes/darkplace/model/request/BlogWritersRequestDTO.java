package com.biagomes.darkplace.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogWritersRequestDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String fullname;

    @NotBlank
    private String username;

    @JsonFormat(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String email;
}
