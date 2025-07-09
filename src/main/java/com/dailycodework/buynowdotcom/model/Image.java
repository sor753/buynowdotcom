package com.dailycodework.buynowdotcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
//全てのフィールドを引数に受け取るコンストラクタ
@AllArgsConstructor
//引数無しのコンストラクタを定義
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String filename;
    private String fileType;
//    ラージ・オブジェクト(LOB):大量のデータを保持するように設計されたデータ型のセット
    @Lob
    private Blob image;

    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
