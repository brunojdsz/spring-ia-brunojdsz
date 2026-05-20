package br.com.brunojdsz.service;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class TranscriptionService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public TranscriptionService(@Value("${spring.ai.openai.api-key}") String apiKey) {
        OpenAiAudioApi openAiAudioApi = OpenAiAudioApi.builder()
                .apiKey(apiKey)
                .build();
        this.transcriptionModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

    public String transcribeAudio(MultipartFile file) throws IOException {
        File tempfile = File.createTempFile("audio", ".mp3");
        file.transferTo(tempfile);

        OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions
                .builder()
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .language("pt")
                .temperature(0f)
                .build();

        FileSystemResource audioFile = new FileSystemResource(tempfile);
        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        AudioTranscriptionResponse response = transcriptionModel.call(transcriptionRequest);

        tempfile.delete();
        return response.getResult().getOutput();
    }
}
