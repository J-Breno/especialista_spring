package com.github.jbreno.algafood.api.controller;

import com.github.jbreno.algafood.api.model.input.PhotoProductInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @Valid PhotoProductInput photoProductInput) {
        var fileName = UUID.randomUUID().toString() + "_" + photoProductInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/Users/breno/Desktop/catalogo", fileName);

        try {
            photoProductInput.getFile().transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
