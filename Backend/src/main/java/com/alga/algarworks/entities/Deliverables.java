package com.alga.algarworks.entities;

import com.alga.algarworks.dtos.DeliverablesDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Deliverables")
@Getter
@Setter
@NoArgsConstructor

public class Deliverables implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @Cascade(CascadeType.REFRESH)
    private List<Cursos> course;

    @NonNull
    @Column(nullable = false)
    @NotBlank(message = "A duração de dias é obrigatório")
    private String supportDurationInDays;

    public DeliverablesDTO toDTO() {
        var deliverablesDTO = new DeliverablesDTO();
        BeanUtils.copyProperties(this, deliverablesDTO);
        return deliverablesDTO;
    }

}

