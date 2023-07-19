package com.soares.webclinica.repository.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "responsavel")
public class ResponsavelEntity implements Serializable {

	private static final long serialVersionUID = 1607308902978915052L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_responsavel", nullable = false, columnDefinition = "binary(16)")
	private UUID idResponsavel;

	@Column(name = "nome_responsavel", columnDefinition = "varchar(50)")
	private String nomeResponsavel;

	@Column(name = "data_nascimento", columnDefinition = "date")
	private LocalDate dataNascimento;

	@Column(name = "cpf", columnDefinition = "varchar(11)")
	private String cpf;
}
