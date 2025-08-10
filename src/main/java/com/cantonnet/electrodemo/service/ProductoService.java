package com.cantonnet.electrodemo.service;
import com.cantonnet.electrodemo.entity.Producto;
import com.cantonnet.electrodemo.entity.Categoria;
import com.cantonnet.electrodemo.repository.CategoriaRepository;
import com.cantonnet.electrodemo.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
    }

    @Transactional
    public Producto crear(String nombre, Double precio, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + categoriaId));

        var p = Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .categoria(categoria)
                .build();

        return productoRepository.save(p);
    }

    @Transactional
    public Producto actualizar(Long id, String nombre, Double precio, Long categoriaId) {
        var prod = obtenerPorId(id);

        if (nombre != null && !nombre.isBlank()) prod.setNombre(nombre);
        if (precio != null) prod.setPrecio(precio);

        if (categoriaId != null) {
            var nuevaCategoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + categoriaId));
            prod.setCategoria(nuevaCategoria);
        }

        return productoRepository.save(prod);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

}
