package com.biagomes.darkplace.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegendsDTO {
   private Long id;
   private String title;
   private String history;
   private BlogWritersDTO blogWritersDTO;
}
