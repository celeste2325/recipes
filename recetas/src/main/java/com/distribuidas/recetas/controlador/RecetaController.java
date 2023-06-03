package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.dto.RecetaDto;
import com.distribuidas.recetas.modelo.dto.response.RecetaResponseDto;
import com.distribuidas.recetas.modelo.dto.response.ReemplazarRecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.entities.RecetaAutorizada;
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
    public ResponseEntity<ReemplazarRecetaResponseDto> reemplazarReceta(@PathVariable Integer idReceta) throws NoExisteUnaRecetaParaElIdIngresadoException {
        return new ResponseEntity<>(this.recetaService.reemplazarReceta(idReceta), HttpStatus.OK);
    }
    //TODO ESTA OK, probar si TRAE SOLO LAS AUTORIZADAS y esta ok la query
    @GetMapping("/inicioApp/{idUsuario}")
    public ResponseEntity<?> devolverRecetasInicio(@PathVariable Integer idUsuario) {
        return new ResponseEntity<>(this.recetaService.devuelve3RecetasInicioApp(idUsuario), HttpStatus.OK);
    }
    //TODO ESTA OK, probar si TRAE SOLO LAS AUTORIZADAS y esta ok la query
    @GetMapping("ordenNombreUsuario/{nombreReceta}")
    public ResponseEntity<?> busquedaRecetaPorNombreOrdenadaPorNombreUsuario(@PathVariable String nombreReceta) {
        return new ResponseEntity<>(this.recetaService.recetasPorNombreOrdenNombreUsuario(nombreReceta), HttpStatus.OK);
    }
    //TODO ESTA OK, TRAE SOLO LAS AUTORIZADAS y respeta el orden
    @GetMapping("ordenAntiguedad/{nombreReceta}")
    public ResponseEntity<?> busquedaRecetaPorNombreOrdenadaPorAntiguedad(@PathVariable String nombreReceta) {
        return new ResponseEntity<>(this.recetaService.busquedaRecetaPorNombreOrdenadaPorAntiguedad(nombreReceta), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecetaAutorizada>> devolverRecetas() {
        return new ResponseEntity<>(this.recetaService.devolverRecetas(), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}/{nombreReceta}")
    public ResponseEntity<?> recetaExistentePorUsuario(@PathVariable Integer idUsuario, @PathVariable String nombreReceta) {
        RecetaAutorizada receta = this.recetaService.recetaExistentePorUsuario(nombreReceta, idUsuario);
        if (receta == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(receta, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("busquedaPorId/{id}")
    public ResponseEntity<?> buscarRecetaPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.recetaService.devolverRecetaPorId(id), HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/request-param")
    public ResponseEntity<List<RecetaAutorizada>> devolverRecetasbyParamQuery(@RequestParam(defaultValue = "") String nombreReceta,
                                                                              @RequestParam(defaultValue = "0") Integer idTipo,
                                                                              @RequestParam(defaultValue = "0") Integer idIngrediente,
                                                                              @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaService.devolverRecetasPorParamQueries(nombreReceta, idTipo, idIngrediente, idUsuario),
                HttpStatus.OK);
    }

    @GetMapping("sinIngrediente/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngrediente(@PathVariable Integer idIngrediente) {
        return new ResponseEntity<>(this.recetaService.devolverRecetasSinIngrediente(idIngrediente), HttpStatus.OK);

    }

    @GetMapping("busquedaParcial/{nombreParcialReceta}")
    public List<RecetaAutorizada> devuelveRecetasPorBusquedaParcial(@PathVariable String nombreParcialReceta) {
        if (nombreParcialReceta.length() >= 3) {
            return this.recetaService.devolverRecetasPorBusquedaParcialNombre(nombreParcialReceta);
        }
        return null;
    }
    //implementar eliminacion logica
    /*@DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReceta(@PathVariable Integer id) {
        try {
            this.recetaService.eliminarReceta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
    //TODO crear validacion que las recetas se muestren siempre y cuando esten autorizadas para otros usuarios q no sean el creador igual para los comentarios
}
