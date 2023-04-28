package com.alga.algarworks.entities;

import com.alga.algarworks.dtos.ProdutosDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produtos implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private ProductStatus status;

    public enum ProductStatus {
        UNPUBLISHED,
        PUBLISHED;
    }

    public ProdutosDTO toDTO() {
        var produtosDTO = new ProdutosDTO();
        BeanUtils.copyProperties(this, produtosDTO);
        return produtosDTO;
    }

}
