package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.multimedia.MultimediaCloudHandler;
import com.distribuidas.recetas.servicios.interfaces.MultimediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/multimedia")
@AllArgsConstructor
public class MultimediaController {
    private MultimediaService multimediaService;
    private MultimediaCloudHandler multimediaCloudHandler;

    @PostMapping
    public ResponseEntity<?> cargarMultimedia(@RequestBody Collection<Multimedia> multimediaByIdPaso) {
        return new ResponseEntity<>(this.multimediaService.cargarMultimediasPaso(multimediaByIdPaso), HttpStatus.OK);
    }

    @GetMapping("/get_url")
    public String GetCertifiedURL(@RequestParam("file_name") String FileRequest,@RequestHeader("user_id") String UserID){
        return multimediaCloudHandler.GetURlSignedForUpload(FileRequest,UserID);
    }
}
