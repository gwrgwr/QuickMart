package com.example.quickmart.service;

import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void store(MultipartFile file, String productId) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ImageDB image = new ImageDB(fileName, file.getContentType(), file.getBytes());
        System.out.println(Arrays.toString(file.getBytes()));
        imageRepository.save(image);
    }

    public ImageDB getImage(String id) {
        return imageRepository.findById(id).orElseThrow();
    }

    public Stream<ImageDB> getAllFiles() {
        return imageRepository.findAll().stream();
    }
}
