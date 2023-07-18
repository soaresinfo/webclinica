package com.soares.webclinica.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contato")
public class ContatoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6890365312949406667L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id_contato", nullable = false, columnDefinition = "binary(16)")
    private UUID idContato;

    @Column(name = "contato", nullable = false, columnDefinition = "varchar(50)")
    private String contato;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "codigo_tipo_contato", referencedColumnName = "codigo_tipo_contato", columnDefinition = "varchar(1)")
    private TipoContatoEntity tipoContato;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", columnDefinition = "binary(16)")
    private PacienteEntity paciente;
}
