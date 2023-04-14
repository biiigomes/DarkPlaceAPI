package com.biagomes.darkplace.model.response;

import com.biagomes.darkplace.model.BlogWriters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegendsResponseDTO {
   private Long id;
   private String title;
   private String history;
   private BlogWriters writer;
}
