package com.biagomes.darkplace.model.DTO;

import java.util.List;

import com.biagomes.darkplace.model.CasesWithoutSolution;
import com.biagomes.darkplace.model.Legends;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogWritersDTO {
    private Long id;
    private String name;
    private String fullname;
    private String username;
    private String email;
    private List<CasesWithoutSolution> casesWithoutSolution;
    private List<Legends> legends;
}
