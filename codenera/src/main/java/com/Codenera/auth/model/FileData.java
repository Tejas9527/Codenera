package com.Codenera.auth.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Assignment")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    @Column(nullable = false)
    private String newDateColumn;

    public FileData() {
        super();
    }

    // Getter and Setter methods for the new date column
    public String getNewDateColumn() {
        return newDateColumn;
    }

    public void setNewDateColumn(String newDateColumn) {
        this.newDateColumn = newDateColumn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
