package com.soares.webclinica.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class PacienteEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 8806311750107426210L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_paciente", nullable = false, columnDefinition = "binary(16)")
	private UUID idPaciente;

	@Column(name = "nome_paciente", nullable = false, columnDefinition = "varchar(50)")
	private String nomePaciente;

	@Column(name = "data_nascimento", nullable = false, columnDefinition = "date")
	private LocalDate dataNascimento;

	@Column(name = "cpf", nullable = false, columnDefinition = "varchar(11)")
	private String cpf;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_responsavel", referencedColumnName = "id_responsavel", columnDefinition = "binary(16)")
	private ResponsavelEntity responsavel;

}
