package eapli.helpdesk.colaborador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import eapli.helpdesk.colaborador.dto.CollaboratorRoleDTO;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Entity
public class RoleCollaborator implements AggregateRoot<CodigoAlfaNumerico>, DTOable<CollaboratorRoleDTO>, Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @EmbeddedId
    private CodigoAlfaNumerico codigoAlfaNumerico;

    @XmlElement
    @JsonProperty
    private eapli.helpdesk.equipa.domain.Designation designation;

    public RoleCollaborator(final CodigoAlfaNumerico codigoAlfaNumerico, final Designation designacao) {
        Preconditions.noneNull(codigoAlfaNumerico, designacao);
        this.codigoAlfaNumerico = codigoAlfaNumerico;
        this.designation = designacao;
    }

    protected RoleCollaborator() {
        // for ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public CodigoAlfaNumerico codigoAlfaNumerico() {
        return identity();
    }

    @Override
    public CodigoAlfaNumerico identity() {
        return this.codigoAlfaNumerico;
    }

    @Override
    public CollaboratorRoleDTO toDTO() {
        return new CollaboratorRoleDTO(this.codigoAlfaNumerico, this.designation.toString());
    }
}
