package com.cantonnet.electrodemo.service;


import com.cantonnet.electrodemo.entity.Categoria;
import com.cantonnet.electrodemo.repository.CategoriaRepository;
import com.cantonnet.electrodemo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + id));
    }

    @Transactional
    public Categoria crear(String nombre) {
        if (categoriaRepository.existsByNombreIgnoreCase(nombre)) {
            throw new IllegalArgumentException("Ya existe una categoría con nombre: " + nombre);
        }
        var c = new Categoria();
        c.setNombre(nombre);
        return categoriaRepository.save(c);
    }

    @Transactional
    public Categoria actualizar(Long id, String nuevoNombre) {
        var cat = obtenerPorId(id);
        if (categoriaRepository.existsByNombreIgnoreCase(nuevoNombre)
                && !cat.getNombre().equalsIgnoreCase(nuevoNombre)) {
            throw new IllegalArgumentException("Ya existe una categoría con nombre: " + nuevoNombre);
        }
        cat.setNombre(nuevoNombre);
        return categoriaRepository.save(cat);
    }

    @Transactional
    public void eliminar(Long id) {
        // Evitar borrar categorías con productos
        if (!productoRepository.findByCategoriaId(id).isEmpty()) {
            throw new IllegalStateException("No se puede eliminar: la categoría tiene productos asociados.");
        }
        categoriaRepository.deleteById(id);
    }

}
