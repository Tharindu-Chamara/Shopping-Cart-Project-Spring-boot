package edu.example.dream_shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String getType;
    @Lob
    private Blob image;
    private String  downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
