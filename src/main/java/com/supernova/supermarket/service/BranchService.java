package com.supernova.supermarket.service;

import com.supernova.supermarket.dto.BranchDTO;
import com.supernova.supermarket.entity.Branch;
import com.supernova.supermarket.exception.BadRequestException;
import com.supernova.supermarket.exception.ResourceNotFoundException;
import com.supernova.supermarket.repository.BranchRepository;
import com.supernova.supermarket.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepo;
    private final SaleRepository saleRepo;

    public Branch create(BranchDTO dto) {
        Branch b = new Branch();
        b.setName(dto.getName());
        b.setAddress(dto.getAddress());
        b.setPhone(dto.getPhone());
        b.setBranchCode(dto.getBranchCode());
        return branchRepo.save(b);
    }

    public List<Branch> findAll() {
        return branchRepo.findAll();
    }

    public Branch findById(Long id) {
        return branchRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada"));
    }

    public Branch update(Long id, BranchDTO dto) {
        Branch b = findById(id);
        b.setName(dto.getName());
        b.setAddress(dto.getAddress());
        b.setPhone(dto.getPhone());
        b.setBranchCode(dto.getBranchCode());
        return branchRepo.save(b);
    }

    public void delete(Long id) {
        Branch b = findById(id);
        boolean hasSales = !saleRepo.findByBranch_BranchId(id).isEmpty();
        if (hasSales) {
            throw new BadRequestException("No se puede eliminar una sucursal con ventas registradas");
        }
        branchRepo.delete(b);
    }
}
