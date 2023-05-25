package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.Receta;
import com.distribuidas.recetas.servicios.interfaces.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas")
@AllArgsConstructor
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @PostMapping()
    public ResponseEntity<?> crearReceta(@RequestBody Receta newReceta) {
        try {
            return new ResponseEntity<Receta>(this.recetaService.altaReceta(newReceta), HttpStatus.CREATED);
        } catch (YaExisteUnaRecetaConMismoNombreYUsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarReceta(@PathVariable Integer id, @RequestBody Receta newReceta) {
        try {
            return new ResponseEntity<>(this.recetaService.updateReceta(id, newReceta), HttpStatus.CREATED);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
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

    @GetMapping()
    public ResponseEntity<List<Receta>> devolverRecetas() {
        return new ResponseEntity<>(this.recetaService.devolverRecetas(), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}/{nombreReceta}")
    public ResponseEntity<?> recetaExistentePorUsuario(@PathVariable Integer idUsuario, @PathVariable String nombreReceta) {
        Receta receta = this.recetaService.recetaExistentePorUsuario(nombreReceta, idUsuario);
        if (receta == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(receta, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRecetaPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.recetaService.devolverRecetaPorId(id), HttpStatus.OK);
        } catch (NoExisteUnaRecetaParaElIdIngresadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/request-param")
    public ResponseEntity<List<Receta>> devolverRecetasbyParamQuery(@RequestParam(defaultValue = "") String nombreReceta,
                                                                    @RequestParam(defaultValue = "0") Integer idTipo,
                                                                    @RequestParam(defaultValue = "0") Integer idIngrediente,
                                                                    @RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.recetaService.devolverRecetasPorParamQueries(nombreReceta, idTipo, idIngrediente, idUsuario),
                HttpStatus.OK);
    }

    @GetMapping("sinIngrediente/{idIngrediente}")
    public ResponseEntity<?> devolverRecetasSinIngrediente(@PathVariable Integer idIngrediente) {
        return new ResponseEntity<>(this.recetaService.devolverRecetasSinIngrediente(idIngrediente), HttpStatus.OK);

        //TODO AGREGAR REEMPLAZAR --> reemplazar es eliminar y despues el post
        //TODO AGREGAR TODAS LAS BUSQUEDAS. LOS FILTROS.-->listo
        //TODO agregar la forma de ordenamiento de cada uno de los filtros
        //TODO agregar datos a la bbdd para probar los filtros
        //TODO probar todos los endpoints
        //TODO agregar cargar multimedia a pasos

    }

}
