package com.cl2.ReyesEspirituBastyCelia.i202030289.controller;

import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDetailDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.dto.FilmDto;
import com.cl2.ReyesEspirituBastyCelia.i202030289.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Pelicula")
public class MaintenanceController {
    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/index")
    public String index(Model model) {
        List<FilmDto> films = maintenanceService.getAllPelicula();
        model.addAttribute("films", films);
        return "pelicula";
    }
    @GetMapping("/detail/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.getPeliculaById(id);
        model.addAttribute("film", filmDetailDto);
        return "pelicula-detail";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.getPeliculaById(id);
        model.addAttribute("film", filmDetailDto);
        return "pelicula-edit";
    }

    @PostMapping("/edit-confirm")
    public String editar(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updatePelicula(filmDetailDto);
        return "redirect:/Pelicula/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.getPeliculaById(id);
        if (filmDetailDto == null) {
            return "redirect:/Pelicula/index";
        }
        model.addAttribute("film", filmDetailDto);
        return "maintenance-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String eliminarConfirm(@PathVariable Integer id) {
        maintenanceService.deletePelicula(id);
        return "redirect:/Pelicula/index";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        return "pelicula-create";
    }

    @PostMapping("/crear-confirm")
    public String crearConfirm(@ModelAttribute FilmDetailDto filmDetailDto) {
        maintenanceService.createPelicula(filmDetailDto);
        return "redirect:/Pelicula/index";
    }

    @PostMapping("/crear")
    public ResponseEntity<FilmDetailDto> crearPelicula(@RequestBody FilmDetailDto filmDetailDto) {
        FilmDetailDto createdFilm = maintenanceService.createPelicula(filmDetailDto);
        return ResponseEntity.ok(createdFilm);
    }
}
