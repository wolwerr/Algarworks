package com.alga.algarworks.dtos;

import com.alga.algarworks.entities.Cursos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CursosDTO implements Serializable {
    private Long id;
    private String name;

    public Cursos toEntity() {
        var cursos = new Cursos();
        BeanUtils.copyProperties(this, cursos);
        return cursos;
    }



}

