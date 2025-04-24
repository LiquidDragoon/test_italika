package com.italika.etbackend.test_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import com.italika.etbackend.test_backend.entity.Producto;

import java.util.List;

@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Producto> rowMapper = (rs, rowNum) -> {
        Producto p = new Producto();
        p.setIdProducto(rs.getLong("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setBloqueo(rs.getBoolean("bloqueo"));
        return p;
    };

    public List<Producto> findAll() {
        return jdbcTemplate.query("EXEC sp_get_all_products", rowMapper);
    }

    public Producto findByIdProducto(long idProducto) {
        return jdbcTemplate.queryForObject("EXEC sp_get_product_by_idProducto ?", rowMapper, idProducto);
    }

    public void save(Producto producto) {
        jdbcTemplate.update("EXEC sp_create_product ?,?,?,?,?",
            producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad(), producto.getBloqueo());
    }

    public void update(Producto producto) {
        jdbcTemplate.update("EXEC sp_update_product ?,?,?,?,?",
            producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad());
    }

    public void delete(Long idProducto) {
        jdbcTemplate.update("EXEC sp_delete_product ?", idProducto);
    }

    public Boolean isBlocked(Long idProducto) {
        return jdbcTemplate.queryForObject("EXEC sp_is_product_blocked ?", Boolean.class, idProducto);
    }
}
