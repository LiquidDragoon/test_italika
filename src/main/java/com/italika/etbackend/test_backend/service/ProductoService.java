package com.italika.etbackend.test_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.italika.etbackend.test_backend.entity.Producto;
import com.italika.etbackend.test_backend.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto obtener(int idProducto) {
        return repository.findByIdProducto(idProducto);
    }

    public void guardar(Producto producto) {
        repository.save(producto);
    }

    public boolean actualizar(Producto producto) {
        if (repository.isBlocked(producto.getIdProducto())) return false;
        repository.update(producto);
        return true;
    }

    public void eliminar(long idProducto) {
        repository.delete(idProducto);
    }
}