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
@CrossOrigin(origins = "*")
public class RecetaController {

    private final RecetaService recetaService;
    private final RecetaMapper recetaMapper;

    @PostMapping()
    public ResponseEntity<?> crearReceta(@RequestBody Receta newReceta) {
        try {
            return new ResponseEntity<Receta>(this.recetaService.altaReceta(newReceta), HttpStatus.CREATED);
        } catch (YaExisteUnaRecetaConMismoNombreYUsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarReceta(@PathVariable Integer id, @RequestBody RecetaDto newReceta) {
        try {
            Receta newRecetaEntity = this.recetaMapper.mapToEntity(newReceta);
            return new ResponseEntity<>(this.recetaMapper.mapResponseDto(this.recetaService.updateReceta(id, newRecetaEntity)), HttpStatus.CREATED);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/reemplazar/{idReceta}")
    public ResponseEntity<?> reemplazarReceta(@PathVariable Integer idReceta) {
        try {
            return new ResponseEntity<>(this.recetaService.reemplazarReceta(idReceta), HttpStatus.OK);
        }catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReceta(@PathVariable Integer id) {
        try {
            this.recetaService.eliminarReceta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/inicioApp")
    public ResponseEntity<?> devolverRecetasInicio(@RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaService.devuelve3RecetasInicioApp(idUsuario), HttpStatus.OK);
    }

    @GetMapping("ordenNombreUsuario/{nombreReceta}")
    public ResponseEntity<?> busquedaRecetaPorNombreOrdenadaPorNombreUsuario(@PathVariable String nombreReceta,
                                                                             @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(
                this.recetaService.recetasPorNombreOrdenNombreUsuario(nombreReceta, idUsuario)), HttpStatus.OK);
    }

    @GetMapping("ordenAntiguedad/{nombreReceta}")
    public ResponseEntity<?> busquedaRecetaPorNombreOrdenadaPorAntiguedad(@PathVariable String nombreReceta, @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaService.busquedaRecetaPorNombreOrdenadaPorAntiguedad(nombreReceta, idUsuario), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecetaResponseDto>> devolverRecetas(@RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetas(idUsuario)), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}/{nombreReceta}")
    public ResponseEntity<?> recetaExistentePorUsuario(@PathVariable Integer idUsuario, @PathVariable String nombreReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException {
        Receta receta = this.recetaService.recetaExistentePorUsuario(nombreReceta, idUsuario);
        if (receta == null) {
            return new ResponseEntity<>(receta,HttpStatus.OK);
        }else {
            return ResponseEntity.badRequest().body(receta);
        }
    }

    @GetMapping("busquedaPorId/{id}")
    public ResponseEntity<?> buscarRecetaPorId(@PathVariable Integer id, @RequestParam(defaultValue = "0") Integer idUsuario) {
        try {
            return new ResponseEntity<>(this.recetaMapper.mapResponseDto(this.recetaService.devolverRecetaPorId(id, idUsuario)), HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/request-param")
    public ResponseEntity<List<Receta>> devolverRecetasbyParamQuery( @RequestParam(defaultValue = "") String nombreReceta,
                                                                               @RequestParam(defaultValue = "0") Integer idTipo,
                                                                               @RequestParam(defaultValue = "0") Integer idIngrediente,
                                                                               @RequestParam(defaultValue = "0") Integer idUsuario,
                                                                                @RequestParam(defaultValue = "0") Integer idUsuarioObligatorio
    ) {
        return new ResponseEntity<>(this.recetaService.busquedaRecetasByParam(nombreReceta, idTipo, idIngrediente, idUsuario, idUsuarioObligatorio),
                HttpStatus.OK);
    }

    @GetMapping("sinIngrediente/nombreReceta/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngredienteOrdenadaPorNombreReceta(@PathVariable Integer idIngrediente,
                                                                                @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetasSinIngredienteOrdenadaPorNombre(idIngrediente,idUsuario)), HttpStatus.OK);

    }

    @GetMapping("sinIngrediente/antiguedad/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngredienteOrdenadaPorAntiguedad(@PathVariable Integer idIngrediente,
                                                                                @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetasSinIngredienteOrdenadaPorAntiguedad(idIngrediente,idUsuario)), HttpStatus.OK);

    }
    @GetMapping("sinIngrediente/nombreUser/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngredienteOrdenadaPorNombreUser(@PathVariable Integer idIngrediente,
                                                                                @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaMapper.mapLisToDto(this.recetaService.devolverRecetasSinIngredienteOrdenadaPorNombreUser(idIngrediente,idUsuario)), HttpStatus.OK);

    }

    @GetMapping("busquedaParcial/{nombreParcialReceta}")
    public List<Receta> devuelveRecetasPorBusquedaParcial(@PathVariable String nombreParcialReceta,
                                                          @RequestParam(defaultValue = "0") Integer idUsuario) {
        if (nombreParcialReceta.length() >= 3) {
            return this.recetaService.devolverRecetasPorBusquedaParcialNombre(nombreParcialReceta, idUsuario);
        }
        return null;
    }

    //TODO AGREGAR REEMPLAZAR --> que antes de eliminar la receta se conserven las calificaciones para guardar en la nueva receta a crear. luego el delete
    //TODO probar de agregar comentarios y calificacion a receta y luego reemplazar
    // TODO hacer la carga de las calificaciones
}
