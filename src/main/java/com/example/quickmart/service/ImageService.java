package com.example.quickmart.service;

import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.domain.product.Product;
import com.example.quickmart.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageDB store(MultipartFile file, Product product) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        return this.imageRepository.save(new ImageDB(fileName, file.getContentType(), file.getBytes(), product));
    }

    public List<ImageDB> storeMultiple(MultipartFile[] files, Product product) throws IOException {
        List<ImageDB> images = new ArrayList<>(List.of());
        for (MultipartFile file : files) {
            images.add(store(file, product));
        }
        return images;
    }

    public ImageDB getImage(String id) {
        return imageRepository.findById(id).orElseThrow();
    }
}
