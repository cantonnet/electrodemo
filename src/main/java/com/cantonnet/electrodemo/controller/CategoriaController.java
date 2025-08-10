package com.cantonnet.electrodemo.controller;

import com.cantonnet.electrodemo.dto.CategoriaReq;
import com.cantonnet.electrodemo.entity.Categoria;
import com.cantonnet.electrodemo.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/Categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria crear(@RequestBody CategoriaReq req) {
        return categoriaService.crear(req.nombre());
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id,@RequestBody CategoriaReq req) {
        return categoriaService.actualizar(id, req.nombre());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
