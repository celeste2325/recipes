package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Tipo;
import com.distribuidas.recetas.servicios.interfaces.TipoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@AllArgsConstructor
public class TipoController {
    private final TipoService tipoService;

    @GetMapping()
    public ResponseEntity<List<Tipo>> devuelveLosTiposDePlatos() {
        return new ResponseEntity<>(this.tipoService.devolverTiposPlatos(), HttpStatus.OK);
    }

    @GetMapping("busquedaParcial/{nombreParcialTipoPlato}")
    public List<Tipo> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialTipoPlato) {
        if (nombreParcialTipoPlato.length() >= 3) {
            return this.tipoService.devolverPlatosPorBusquedaParcialTipoDePlato(nombreParcialTipoPlato);
        }
        return null;//agregar excepcion
    }
    //TODO buscar tipo por id
}
