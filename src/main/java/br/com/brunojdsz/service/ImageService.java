package br.com.brunojdsz.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, Integer qtdImage, Integer height, Integer width) {
        //ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(prompt));
        ImageResponse response = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("gpt-image-1-mini")
                                .quality(quality)
                                .N(qtdImage)
                                .height(height)
                                .width(width)
                                .build())

        );

        return response;
    }
}
