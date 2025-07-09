package com.dailycodework.buynowdotcom.controller;

import com.dailycodework.buynowdotcom.dtos.ProductDto;
import com.dailycodework.buynowdotcom.model.Category;
import com.dailycodework.buynowdotcom.model.Product;
import com.dailycodework.buynowdotcom.request.AddProductRequest;
import com.dailycodework.buynowdotcom.request.ProductUpdateRequest;
import com.dailycodework.buynowdotcom.response.ApiResponse;
import com.dailycodework.buynowdotcom.service.product.IProductService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("products/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found!", productDto));
    }

    @GetMapping("products/category/brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(
//            リクエストパラメータ（クエリパラメータ）を受け取る
//            引数名とリクエストパラメータ名を一致させることで()を省略可
            @RequestParam("category") String category, @RequestParam("brand") String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("products/category")
    public ResponseEntity<ApiResponse> getProductsByCategory(
            @RequestParam("category") String category) {
        List<Product> products = productService.getProductsByCategory(category);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("products/brand/name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(
            @RequestParam("brand") String brand, @RequestParam("name") String name) {
        List<Product> products = productService.getProductsByBrandAndName(brand, name);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("products/brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(
            @RequestParam("brand") String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("products/{name}/products")
    public ResponseEntity<ApiResponse> getProductsByName(
            @PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
            Product theProduct = productService.addProduct(product);
            ProductDto productDto = productService.convertToDto(theProduct);
            return ResponseEntity.ok(new ApiResponse("Success", productDto));
    }

    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(
            @RequestBody ProductUpdateRequest product, @PathVariable Long id) {
            Product theProduct = productService.updateProduct(product, id);
            ProductDto productDto = productService.convertToDto(theProduct);
            return ResponseEntity.ok(new ApiResponse("Success", productDto));
    }

    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success", id));
    }
}
