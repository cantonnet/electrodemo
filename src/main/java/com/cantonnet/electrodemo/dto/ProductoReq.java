package com.cantonnet.electrodemo.dto;

public record ProductoReq (
        String nombre,
        double precio,
        Long categoriaId
){ }
