package com.cl2.ReyesEspirituBastyCelia.i202030289.service;

import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDetailDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.entity.Film;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> getAllPelicula();

    FilmDetailDto getPeliculaById(int id);

    Boolean deletePelicula(int id);

    Boolean updatePelicula(FilmDetailDto filmDetailDto);

    FilmDetailDto createPelicula(FilmDetailDto filmDetailDto);
}
