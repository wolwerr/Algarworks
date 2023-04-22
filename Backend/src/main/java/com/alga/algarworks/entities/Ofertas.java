package com.alga.algarworks.entities;

import com.alga.algarworks.dtos.OfertasDTO;
import jakarta.persistence.*;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ofertas  {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String code;
    @NonNull
    @Column(nullable = false)
    private String internalName;

    @NonNull
    @Column(nullable = false)
    private boolean active;

    @OneToOne
    @Cascade(CascadeType.REFRESH)
    private Produtos product;
    @NonNull
    @Column(nullable = false)
    private String salesStartingAt;
    @NonNull
    @Column(nullable = false)
    private String salesEndingAt;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    private List<Cursos> deliverables;
    @NonNull
    @Column(nullable = false)
    private String supportDurationInDays;


    public OfertasDTO toDTO() {
        var ofertasDTO = new OfertasDTO();
        BeanUtils.copyProperties(this, ofertasDTO);
        return ofertasDTO;
    }

}
