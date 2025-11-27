package com.supernova.supermarket.service;

import com.supernova.supermarket.dto.ProductDTO;
import com.supernova.supermarket.entity.Product;
import com.supernova.supermarket.exception.BadRequestException;
import com.supernova.supermarket.exception.ResourceNotFoundException;
import com.supernova.supermarket.repository.ProductRepository;
import com.supernova.supermarket.repository.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final SaleDetailRepository saleDetailRepo;

    public Product create(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setBarcode(dto.getBarcode());
        return productRepo.save(p);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public List<Product> findByCategory(String category) {
        return productRepo.findByCategory(category);
    }

    public Product update(Long id, ProductDTO dto) {
        Product p = findById(id);
        p.setName(dto.getName());
        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setBarcode(dto.getBarcode());
        return productRepo.save(p);
    }

    public void delete(Long id) {
        long count = saleDetailRepo.countByProduct_ProductId(id);
        if (count > 0) {
            throw new BadRequestException("No se puede eliminar producto con ventas asociadas");
        }
        Product p = findById(id);
        productRepo.delete(p);
    }
}
