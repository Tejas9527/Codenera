package com.Codenera.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Codenera.auth.model.FileTest;

@Repository
public interface FileTestRepository extends JpaRepository<FileTest, Long> {
    // Add custom query methods if needed
}
