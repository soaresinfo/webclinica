package com.soares.webclinica.repository.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exame")
public class ExameEntity implements Serializable {

	private static final long serialVersionUID = -6077748560035204487L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_exame", nullable = false, columnDefinition = "binary(16)")
	private UUID idExame;

	@Column(name = "text", columnDefinition = "text")
	private String texto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", columnDefinition = "BINARY(16)")
	private PacienteEntity paciente;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta", columnDefinition = "BINARY(16)")
	private ConsultaEntity consulta;
}
