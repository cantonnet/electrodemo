package com.cantonnet.electrodemo.controller;


import com.cantonnet.electrodemo.dto.ProductoReq;
import com.cantonnet.electrodemo.entity.Producto;
import com.cantonnet.electrodemo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("Productos")
@RequiredArgsConstructor
@RestController
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Producto> listarPorCategoria(@PathVariable Long categoriaId) {
        return productoService.listarPorCategoria(categoriaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody ProductoReq req) {
        return productoService.crear(req.nombre(), req.precio(), req.categoriaId());
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id,@RequestBody ProductoReq req) {
        return productoService.actualizar(id, req.nombre(), req.precio(), req.categoriaId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
