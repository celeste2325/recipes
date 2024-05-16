package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Multimedia;
import com.distribuidas.recipe.multimedia.MultimediaCloudHandler;
import com.distribuidas.recipe.service.interfaces.MultimediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/multimedia")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MultimediaController {
    private MultimediaService multimediaService;
    private MultimediaCloudHandler multimediaCloudHandler;

    @PostMapping
    public ResponseEntity<?> saveMultimedia(@RequestBody Collection<Multimedia> multimediaByStepID) {
        return new ResponseEntity<>(this.multimediaService.saveMultimediasStep(multimediaByStepID), HttpStatus.OK);
    }
    @GetMapping("/get_url")
    public String GetCertifiedURL(@RequestParam("file_name") String FileRequest,@RequestHeader("user_id") String UserID){
        return multimediaCloudHandler.GetURlSignedForUpload(FileRequest,UserID);
    }
}
