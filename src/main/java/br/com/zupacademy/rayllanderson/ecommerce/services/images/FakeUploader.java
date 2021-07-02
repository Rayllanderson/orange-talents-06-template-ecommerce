package br.com.zupacademy.rayllanderson.ecommerce.services.images;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeUploader implements Uploader {
    @Override
    public Set<String> upload(List<MultipartFile> images) {
        String baseLink = "https://rayllanderson/imagens/";
        return images.stream().map(image -> baseLink + image.getOriginalFilename()).collect(Collectors.toSet());
    }
}
