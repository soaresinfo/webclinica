package com.soares.webclinica.repository.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "consulta")
public class ConsultaEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -2565162926404990896L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_consulta", nullable = false, columnDefinition = "binary(16)")
	private UUID idConsulta;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", columnDefinition = "binary(16)")
	private PacienteEntity paciente;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_medico", referencedColumnName = "id_medico", columnDefinition = "binary(16)")
	private MedicoEntity medico;

	@Column(name = "data_consulta", columnDefinition = "datetime")
	private LocalDateTime dataConsulta;

	@Column(name = "data_retorno", columnDefinition = "datetime")
	private LocalDateTime dataRetorno;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_convenio", referencedColumnName = "id_convenio", columnDefinition = "binary(16)")
	private ConvenioEntity convenio;
}
