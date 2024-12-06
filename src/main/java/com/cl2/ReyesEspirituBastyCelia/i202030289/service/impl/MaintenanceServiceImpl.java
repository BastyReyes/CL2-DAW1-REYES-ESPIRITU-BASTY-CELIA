package com.cl2.ReyesEspirituBastyCelia.i202030289.service.impl;

import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDetailDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.entity.Film;
import com.cl2.ReyesEspirituBastyCelia.i202030289.entity.Language;
import com.cl2.ReyesEspirituBastyCelia.i202030289.repository.FilmRepository;
import com.cl2.ReyesEspirituBastyCelia.i202030289.repository.LanguageRepository;
import com.cl2.ReyesEspirituBastyCelia.i202030289.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<FilmDto> getAllPelicula() {
        List<FilmDto> films = new ArrayList<>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalRate());
            films.add(filmDto);
        });

        return films;
    }

    @Override
    public FilmDetailDto getPeliculaById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate()))
                .orElse(null);
    }

    @Override
    public Boolean updatePelicula(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(film -> {
            film.setTitle(filmDetailDto.title());
            film.setDescription(filmDetailDto.description());
            film.setReleaseYear(filmDetailDto.releaseYear());
            film.setRentalDuration(filmDetailDto.rentalDuration());
            film.setRentalRate(filmDetailDto.rentalRate());
            film.setLength(filmDetailDto.length());
            film.setReplacementCost(filmDetailDto.replacementCost());
            film.setRating(filmDetailDto.rating());
            film.setSpecialFeatures(filmDetailDto.specialFeatures());

            LocalDateTime localDateTime = Instant.ofEpochMilli(new java.util.Date().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            film.setLastUpdate(localDateTime);

            filmRepository.save(film);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean deletePelicula(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> {
            filmRepository.delete(film);
            return true;
        }).orElse(false);
    }

    @Override
    public FilmDetailDto createPelicula(FilmDetailDto filmDetailDto) {
        Film film = new Film();
        film.setTitle(filmDetailDto.title());
        film.setDescription(filmDetailDto.description());
        film.setReleaseYear(filmDetailDto.releaseYear());
        film.setRentalDuration(filmDetailDto.rentalDuration());
        film.setRentalRate(filmDetailDto.rentalRate());
        film.setLength(filmDetailDto.length());
        film.setReplacementCost(filmDetailDto.replacementCost());
        film.setRating(filmDetailDto.rating());
        film.setSpecialFeatures(filmDetailDto.specialFeatures());
        LocalDateTime localDateTime = Instant.ofEpochMilli(new java.util.Date().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        film.setLastUpdate(localDateTime);
        Optional<Language> languageOptional = languageRepository.findById(filmDetailDto.languageId());
        if (languageOptional.isPresent()) {
            film.setLanguage(languageOptional.get());
        } else {
            Language newLanguage = new Language();
            newLanguage.setLanguageId(filmDetailDto.languageId());
            film.setLanguage(newLanguage);}
        Film savedFilm = filmRepository.save(film);
        return new FilmDetailDto(
                savedFilm.getFilmId(),
                savedFilm.getTitle(),
                savedFilm.getDescription(),
                savedFilm.getReleaseYear(),
                savedFilm.getLanguage().getLanguageId(),
                savedFilm.getLanguage().getName(),
                savedFilm.getRentalDuration(),
                savedFilm.getRentalRate(),
                savedFilm.getLength(),
                savedFilm.getReplacementCost(),
                savedFilm.getRating(),
                savedFilm.getSpecialFeatures(),
                savedFilm.getLastUpdate()
        );
    }
}