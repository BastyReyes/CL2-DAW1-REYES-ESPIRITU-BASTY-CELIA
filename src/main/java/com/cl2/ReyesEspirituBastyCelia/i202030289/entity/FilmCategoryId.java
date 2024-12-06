package com.cl2.ReyesEspirituBastyCelia.i202030289.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class FilmCategoryId {
    private Integer filmId;
    private Integer categoryId;
}

