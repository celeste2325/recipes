package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.dto.RecetaDto;
import com.distribuidas.recetas.modelo.dto.response.RecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.mapstruct.RecetaMapper;
import com.distribuidas.recetas.servicios.interfaces.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas")
@AllArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;
    private final RecetaMapper recetaMapper;

    //TODO OK EL ERROR; OK EL SAVE
    @PostMapping()
    public ResponseEntity<?> crearReceta(@RequestBody Receta newReceta) {
        try {
            return new ResponseEntity<Receta>(this.recetaService.altaReceta(newReceta), HttpStatus.CREATED);
        } catch (YaExisteUnaRecetaConMismoNombreYUsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //TODO OK EL ERROR; OK update --> evaluar si quiero q devuelva la info de los ingredients, unidades.
    @PutMapping("/{id}")
    public ResponseEntity<?> editarReceta(@PathVariable Integer id, @RequestBody RecetaDto newReceta) {
        try {
            Receta newRecetaEntity = this.recetaMapper.mapToEntity(newReceta);
            return new ResponseEntity<>(this.recetaMapper.mapResponseDto(this.recetaService.updateReceta(id, newRecetaEntity)), HttpStatus.CREATED);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //TODO OK EL ERROR; OK delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReceta(@PathVariable Integer id) {
        try {
            this.recetaService.eliminarReceta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //TODO OK
    @GetMapping()
    public ResponseEntity<List<RecetaResponseDto>> devolverRecetas() {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetas()), HttpStatus.OK);
    }

    //TODO OK
    @GetMapping("/{idUsuario}/{nombreReceta}")
    public ResponseEntity<?> recetaExistentePorUsuario(@PathVariable Integer idUsuario, @PathVariable String nombreReceta) {
        Receta receta = this.recetaService.recetaExistentePorUsuario(nombreReceta, idUsuario);
        if (receta == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(this.recetaMapper.mapResponseDto(receta), HttpStatus.BAD_REQUEST);
    }

    //TODO OK EL ERROR; OK
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRecetaPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.recetaMapper.mapResponseDto(this.recetaService.devolverRecetaPorId(id)), HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //TODO OK idIngrediente,nombreReceta,idTipo,idUsuario ;
    @GetMapping("/request-param")
    public ResponseEntity<List<RecetaResponseDto>> devolverRecetasbyParamQuery(@RequestParam(defaultValue = "") String nombreReceta,
                                                                               @RequestParam(defaultValue = "0") Integer idTipo,
                                                                               @RequestParam(defaultValue = "0") Integer idIngrediente,
                                                                               @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetasPorParamQueries(nombreReceta, idTipo, idIngrediente, idUsuario)),
                HttpStatus.OK);
    }

    //TODO OK ;
    @GetMapping("sinIngrediente/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngrediente(@PathVariable Integer idIngrediente) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetasSinIngrediente(idIngrediente)), HttpStatus.OK);

        //TODO AGREGAR REEMPLAZAR --> reemplazar es eliminar y despues el post
        //TODO agregar la forma de ordenamiento de cada uno de los filtros
        //TODO probar todos los endpoints --> falta con los ordenamientos
        //TODO probar de agregar comentarios y calificacion a receta y luego reemplazar

    }

}
