package com.italika.etbackend.test_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italika.etbackend.test_backend.entity.Producto;
import com.italika.etbackend.test_backend.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable int id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public void guardar(@RequestBody Producto producto) {
        service.guardar(producto);
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Producto producto) {
        if (!service.actualizar(producto)) {
            return ResponseEntity.badRequest().body("Producto en estado de bloqueo lógico, no se puede actualizar");
        }
        return ResponseEntity.ok("Actualizado");
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}