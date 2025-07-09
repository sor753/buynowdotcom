package com.dailycodework.buynowdotcom.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
//引数なしのコンストラクタ（デフォルトコンストラクタ）
@NoArgsConstructor
//JPA のエンティティクラスには引数無しコンストラクタが必要なため、
//上の @NoArgsConstructor をアノテートしておく
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

//    １つのカテゴリに複数の製品を紐づけられる
    @ManyToOne(cascade = CascadeType.ALL)
//    結合するカラムを指定する
    @JoinColumn(name = "category_id")
    private Category category;

//    １つの製品に複数の画像を紐づけられる
//    オーナー側に mappedBy を指定する
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product(String name, String brand, BigDecimal price, int inventory, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
    }
}
