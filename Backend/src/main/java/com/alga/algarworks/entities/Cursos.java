package com.alga.algarworks.entities;


import com.alga.algarworks.dtos.CursosDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Entity(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class proCursos implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(nullable = false, length = 64)
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    public CursosDTO toDTO() {
        var cursosDTO = new CursosDTO();
        BeanUtils.copyProperties(this, cursosDTO);
        return cursosDTO;
    }
}
