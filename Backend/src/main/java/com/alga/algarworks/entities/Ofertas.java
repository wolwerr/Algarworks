package com.alga.algarworks.entities;

import com.alga.algarworks.dtos.OfertasDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.BeanUtils;

import java.util.List;


@Entity(name = "Offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ofertas  {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @NotBlank(message = "O code é obrigatório")
    private String code;
    @NonNull
    @Column(nullable = false)
    @NotBlank(message = "O internalName é obrigatório")
    private String internalName;
    @NonNull
    @Column(nullable = false)
    private double price;
    @NonNull
    @Column(nullable = false)
    private boolean active;

    @OneToOne
    @Cascade(CascadeType.REFRESH)
    private Produtos product;
    @NonNull
    @Column(nullable = false)
    @NotBlank(message = "A data é obrigatória")
    private String salesStartingAt;
    @NonNull
    @Column(nullable = false)
    @NotBlank(message = "A data é obrigatória")
    private String salesEndingAt;
    @OneToMany
    @Cascade(CascadeType.REFRESH)
    private List<Cursos> deliverables;
    @NonNull
    @Column(nullable = false)
    @NotBlank(message = "A duração de dias é obrigatório")
    private String supportDurationInDays;


    public OfertasDTO toDTO() {
        var ofertasDTO = new OfertasDTO();
        BeanUtils.copyProperties(this, ofertasDTO);
        return ofertasDTO;
    }

}
