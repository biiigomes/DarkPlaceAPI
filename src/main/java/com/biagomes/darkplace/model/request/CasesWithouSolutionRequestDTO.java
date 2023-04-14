package com.biagomes.darkplace.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CasesWithouSolutionRequestDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String history;

    @JsonIgnore
    @NotNull
    private Long writer;
}
