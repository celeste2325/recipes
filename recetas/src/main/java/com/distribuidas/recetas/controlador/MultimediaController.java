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

    /* les explico la idea es primero obtener la URL firmada por S3 para subir el archivo se podria subir directamente por el telefono pero es muy inseguro
     este metodo recibe 2 valores FileRequest o nombre del archivo y user_id que seria el id de usuario
     la idea de esto que cada usuario tenga su carpeta asi el s3 esta mas ordenado

     ejemplo del codigod e front de como seria obtener esta URLFirmada
        var myHeaders = new Headers();
        myHeaders.append("user_id", "1234");

        var requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
            };

            fetch("localhost:8080/multimedia/get_url?file_name=fotolinda.jpg", requestOptions)
                .then(response => response.text())
                .then(result => console.log(result))
                .catch(error => console.log('error', error));

         ///////////////////////////////////////////////////////////////////
         depues de obtenida la URL firmada para subir el archivo seria asi :
         ///////////////////////////////////////////////////////////////////
         const uploadToS3 = async (presignedUrl, fileUri) => {
             try {

             // aqui obtienen el archivo del telefono
                 let file = await fetch(fileUri);

             // aqui lo convierten en un buffer/array de bytes
                 let blob = await file.blob();

            // aqui lo suben a S3
                let response = await fetch(presignedUrl, {
                method: 'PUT',
                body: blob,
                 headers: {
                'Content-Type': 'image/jpeg'},
                });

            // si la respuesta no es OK
                if (!response.ok) {
                throw new Error('Upload to S3 failed: ' + response.statusText);
                }

                console.log('Upload to S3 was successful');
                } catch (error) {
                console.log('Error uploading to S3: ' + error);
                }
                };

    */
    @GetMapping("/get_url")
    public String GetCertifiedURL(@RequestParam("file_name") String FileRequest,@RequestHeader("user_id") String UserID){
        return multimediaCloudHandler.GetURlSignedForUpload(FileRequest,UserID);
    }
}
