package com.biagomes.darkplace.model.request;

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
public class LegendsRequestDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String history;

    @NotNull
    private Long writer;
}
