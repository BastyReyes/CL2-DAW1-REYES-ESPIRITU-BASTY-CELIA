package com.cl2.ReyesEspirituBastyCelia.i202030289.dto;

import java.time.LocalDateTime;

public record FilmDetailDto(Integer filmId,
                            String title,
                            String description,
                            Integer releaseYear,
                            Integer languageId,
                            String languageName,
                            Integer rentalDuration,
                            Double rentalRate,
                            Integer length,
                            Double replacementCost,
                            String rating,
                            String specialFeatures,
                            LocalDateTime lastUpdate) {
}
