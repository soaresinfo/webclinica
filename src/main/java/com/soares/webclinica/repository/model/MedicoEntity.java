package com.soares.webclinica.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medico")
public class MedicoEntity implements Serializable {

	@Serial
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
