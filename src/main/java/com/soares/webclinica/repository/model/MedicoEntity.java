package com.soares.webclinica.repository.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "medico")
public class MedicoEntity implements Serializable {

	private static final long serialVersionUID = -1081607996585989720L;

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id_medico", nullable = false, columnDefinition = "BINARY(16)")
	private UUID idMedico;

	@Column(name = "nome_medico", nullable = false, columnDefinition = "varchar(50)")
	private String nomeMedico;

	@Column(name = "crm", nullable = false, columnDefinition = "varchar(10)")
	private String crm;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private List<ConvenioEntity> convenios;
}
