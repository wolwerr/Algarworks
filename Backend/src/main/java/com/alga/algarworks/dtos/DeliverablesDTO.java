package com.alga.algarworks.dtos;

import com.alga.algarworks.entities.Cursos;
import com.alga.algarworks.entities.Deliverables;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliverablesDTO implements Serializable {

    private Long id;
    private List<Cursos> course;
    private String supportDurationInDays;

    public Deliverables toEntity() {
        var deliverables = new Deliverables();
        BeanUtils.copyProperties(this, deliverables, "course");
        return deliverables;
    }

}