package com.alga.algarworks.entities;


import com.alga.algarworks.dtos.CursosDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Entity(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cursos implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(nullable = false, length = 64)
    private String name;

    public CursosDTO toDTO() {
        var cursosDTO = new CursosDTO();
        BeanUtils.copyProperties(this, cursosDTO);
        return cursosDTO;
    }
}
