package com.dailycodework.buynowdotcom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    シリアライズ、デシリアライズの際に無視する
    @JsonIgnore
//    １つのカテゴリに複数の製品を紐づけられる
//    オーナー側に mappedBy を指定する(Product の方には指定してない)
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }
}