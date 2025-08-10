package com.cantonnet.electrodemo.repository;

import com.cantonnet.electrodemo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

    //Listar los productos de la categoria
    List<Producto> findByCategoriaId(Long categoriaId);

}
