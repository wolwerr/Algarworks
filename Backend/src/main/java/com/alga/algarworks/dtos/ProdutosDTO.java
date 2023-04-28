package com.alga.algarworks.dtos;

import com.alga.algarworks.entities.Produtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProdutosDTO implements Serializable {
    private Long id;
    private String name;
    private Produtos.ProductStatus status;

    public Produtos toEntity() {
        var produtos = new Produtos();
        BeanUtils.copyProperties(this, produtos);
        return produtos;
    }

    public ProdutosDTO(Produtos produtos) {
        BeanUtils.copyProperties(produtos, this);
    }

}
