package com.alga.algarworks.dtos;


import com.alga.algarworks.entities.Cursos;
import com.alga.algarworks.entities.Ofertas;
import com.alga.algarworks.entities.Produtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OfertasDTO implements Serializable {
    private String code;
    private String internalName;
    private double price;
    private boolean active;
    private Produtos product;
    private String salesStartingAt;
    private String salesEndingAt;
    private List<Cursos> deliverables;
    private String supportDurationInDays;

    public Ofertas toEntity() {
        var ofertas = new Ofertas();
        BeanUtils.copyProperties(this, ofertas, "product", "deliverables");
        return ofertas;
    }

}

