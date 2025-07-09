package com.dailycodework.buynowdotcom.request;

import com.dailycodework.buynowdotcom.model.Category;
import lombok.Data;

import java.math.BigDecimal;

//@ToString、@Getter、@Setter、@EqualsAndHashCode、@RequiredArgsConstructorの
// 5つのアノテーションをまとめて付与
@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
