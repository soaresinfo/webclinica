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
@Table(name = "convenio")
public class ConvenioEntity implements Serializable {

	private static final long serialVersionUID = 93471137984800623L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_convenio", nullable = false, columnDefinition = "BINARY(16)")
	private UUID idConvenio;

	@Column(name = "nome_convenio", nullable = false, columnDefinition = "varchar(45)")
	private String nomeConvenio;

	@Column(name = "codigo_convenio", nullable = false, columnDefinition = "varchar(10)")
	private String codigoConvenio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_medico", referencedColumnName = "id_medico", nullable = false, columnDefinition = "binary(16)")
	private MedicoEntity idMedico;
}
