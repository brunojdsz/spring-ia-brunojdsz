package br.com.brunojdsz.controller;

import br.com.brunojdsz.service.ChatService;
import br.com.brunojdsz.service.ImageService;
import br.com.brunojdsz.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController
public class GenerativeAIController {

    private final ChatService service;
    private final RecipeService recipeService;
    private final ImageService imageService;

    public GenerativeAIController(ChatService service, RecipeService recipeService, ImageService imageService) {
        this.service = service;
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return service.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt){
        return service.getResponseWithOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "none") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

    @GetMapping("generate-image")
    public ResponseEntity<?> generateImage(@RequestParam String prompt,
                                      @RequestParam(defaultValue = "low") String quality,
                                      @RequestParam(defaultValue = "1") Integer qtdImage,
                                      @RequestParam(defaultValue = "1024") Integer height,
                                      @RequestParam(defaultValue = "1024") Integer width){
        ImageResponse response = imageService.generateImage(prompt, quality, qtdImage, height, width);

        String base64 = response.getResults()
                .get(0)
                .getOutput()
                .getB64Json();

        byte[] imageBytes = Base64.getDecoder().decode(base64);


        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}
