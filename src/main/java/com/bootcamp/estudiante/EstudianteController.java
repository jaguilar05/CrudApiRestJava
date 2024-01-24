package com.bootcamp.estudiante;


import com.bootcamp.libro.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")
public class EstudianteController {


    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }
    @GetMapping
    public List<Estudiante> getAllEstudiantes(

            @RequestParam(value = "primerNombre",required = false) String primerNombre,
            @RequestParam(value = "primerApellido", required = false) String primerApellido
    ){
        if (primerNombre != null || primerApellido != null){

          return  estudianteService.getEstudiantesByPrimerNombreOrPrimerApellido(primerNombre,primerApellido);

        }
        return estudianteService.getAllEstudiantes();

    }

    @GetMapping("{id}")
    public Estudiante getUnEstudiante(@PathVariable long id){
        return estudianteService.getUnEstudiante(id);
    }

    @PostMapping
    public void createEstudiante(@RequestBody Estudiante e){
        System.out.println("Controller: create estudiante entered");
        estudianteService.createEstudiante(e);
        System.out.println("controller create estudiante exited");

    }

    @DeleteMapping("{id}")
    public void deleteEstudiante(@PathVariable("id") Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);

    }


    @PutMapping("{id}")
    public Estudiante updateEstudiante(@PathVariable Long id,@RequestBody Estudiante estudianteAActualizar){
       return estudianteService.updateEstudiante(id, estudianteAActualizar);
    }

    @PostMapping("{estudiante_id}/libros")
    public Estudiante agregarLibroAEstudiante(@PathVariable("estudiante_id") Long estudianteId, @RequestBody Libro libro){
        return estudianteService.agregarLibroAEstudiante(estudianteId,libro);
    }

}
