package com.parameta.pruebaParameta.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class ValidDate {
    private boolean valid;
    private String date;
}
