package com.supernova.supermarket.controller;

import com.supernova.supermarket.dto.BranchDTO;
import com.supernova.supermarket.entity.Branch;
import com.supernova.supermarket.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService service;

    @PostMapping("/")
    public ResponseEntity<Branch> create(@RequestBody BranchDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/")
    public List<Branch> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Branch get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Branch update(@PathVariable Long id, @RequestBody BranchDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
