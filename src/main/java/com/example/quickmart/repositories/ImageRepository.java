package com.example.quickmart.repositories;

import com.example.quickmart.domain.image.ImageDB;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageDB, String> {}
