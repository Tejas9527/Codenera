package com.Codenera.auth.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class FileTest {
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

    
    public FileTest()
    {
    	
    }
	public FileTest(Long id, String fileName, byte[] data, String newDateColumn) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.data = data;
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

	public String getNewDateColumn() {
		return newDateColumn;
	}

	public void setNewDateColumn(String newDateColumn) {
		this.newDateColumn = newDateColumn;
	}
    
    
}