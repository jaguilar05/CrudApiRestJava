package com.bootcamp.estudiante;

import com.bootcamp.libro.Libro;
import com.bootcamp.libro.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EstudianteService {

    private EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;
    @Autowired
    public EstudianteService( EstudianteRepository estudianteRepository, LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> getAllEstudiantes(){

      List<Estudiante> estudiantes=  estudianteRepository.findAll();
      //logica de negocio
        return estudiantes;
    }

    public List<Estudiante> getEstudiantesByPrimerNombreOrPrimerApellido(String primerNombre,String primerApellido){

        List<Estudiante> estudiantes=  estudianteRepository.findEstudianteByPrimerNombreOrPrimerApellido(primerNombre,primerApellido);
        //logica de negocio
        return estudiantes;
    }


    public void createEstudiante(Estudiante e){

        //check si el email ya existe
        boolean emailExiste =  estudianteRepository.existsByEmail(e.getEmail());
        if (emailExiste){
            throw new IllegalArgumentException(e.getEmail()+ "Ya esta registrado");

        }

        estudianteRepository.save(e);


    }

    public void deleteEstudiante(Long estudianteId) {
        //check si id existe, si no imprimimos algo
        boolean estudianteExiste = estudianteRepository.existsById(estudianteId);


        if(!estudianteExiste){
            throw new NoSuchElementException("estudiante con ese id no existe");
        }

        estudianteRepository.deleteById(estudianteId);

    }



    public Estudiante updateEstudiante(Long id, Estudiante estudianteAActualizar){
    //check de estudiante con ese id existe, si no botamos un error
     Estudiante estudianteExistente =    estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con ese id no exitste,id:" +id));
        //check si el email es valido


       //check si el email que se quiere actualizar existe

           boolean emailExiste =  estudianteRepository.existsByEmailAndIdIsNot(estudianteAActualizar.getEmail(),estudianteExistente.getId());
           if (emailExiste){
               throw new IllegalArgumentException(estudianteAActualizar.getEmail()+ "Ya esta registrado");

           }

        //Actualizar estudiante

      estudianteExistente.setPrimerNombre(estudianteAActualizar.getPrimerNombre());
        estudianteExistente.setSegundoNombre(estudianteAActualizar.getSegundoNombre());
        estudianteExistente.setPrimerApellido(estudianteAActualizar.getPrimerApellido());
        estudianteExistente.setSegundoApellido(estudianteAActualizar.getSegundoApellido());
        estudianteExistente.setFechaNacimiento(estudianteAActualizar.getFechaNacimiento());
        estudianteExistente.setEmail(estudianteAActualizar.getEmail());

       return estudianteRepository.save(estudianteExistente);
    }

    public Estudiante getUnEstudiante(long id){
        Optional<Estudiante> estudianteOptional =   estudianteRepository.findById(id);

        if (estudianteOptional.isEmpty()){
            throw new NoSuchElementException("Estudiante con ese id no existe");

        }else {
            return estudianteOptional.get();
        }


    }

    public Estudiante agregarLibroAEstudiante(Long estudianteId, Libro libro){
        Estudiante estudianteExistente =    estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con ese id no exitste,id:" +estudianteId));

        libro.setEstudiante(estudianteExistente);

        libroRepository.save(libro);

        return estudianteExistente;

    }





}
