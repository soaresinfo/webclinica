package com.soares.webclinica.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_contato")
public class TipoContatoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2609907095071208521L;

    @Id
    @Column(name = "codigo_tipo_contato", nullable = false, columnDefinition = "varchar(1)")
    private String codigo_tipo_contato;

    @Column(name = "descricao", nullable = false, columnDefinition = "varchar(50)")
    private String descricao;
}
