package com.soares.webclinica.repository.model;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "exame")
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
