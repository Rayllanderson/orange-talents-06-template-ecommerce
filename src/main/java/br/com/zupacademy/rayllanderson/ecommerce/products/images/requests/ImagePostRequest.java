package br.com.zupacademy.rayllanderson.ecommerce.products.images.requests;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagePostRequest {

    @NotNull
    @Size(min = 1)
    private final List<MultipartFile> files = new ArrayList<>();

    public ImagePostRequest(@NotNull @NotEmpty List<MultipartFile> files, Long productId) {
        Assert.notNull(files, "Deve enviar, no mínimo, uma imagem");
        this.files.addAll(files);
    }

    public List<MultipartFile> getFiles() {
        return files;
    }
}
