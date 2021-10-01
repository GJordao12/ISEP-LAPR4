package eapli.helpdesk.equipa.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import eapli.helpdesk.colaborador.domain.*;
import eapli.helpdesk.equipa.dto.TeamDTO;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Entity
public class Team implements AggregateRoot<CodigoAlfaNumerico>, DTOable<TeamDTO> {
    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @EmbeddedId
    private CodigoAlfaNumerico codigoAlfaNumerico;

    @XmlElement
    @JsonProperty
    private Designation designation;

    @XmlElement
    @JsonProperty
    private Acronym acronym;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "TEAM_RESPONSIBLES", joinColumns = @JoinColumn(name = "TEAM_CODE"),
            inverseJoinColumns = @JoinColumn(name = "RESPONSIBLE_MECNUMBER"))
    private List<Collaborator> listResponsibles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "TEAM_MEMBERS", joinColumns = @JoinColumn(name = "TEAM_CODE"),
            inverseJoinColumns = @JoinColumn(name = "MEMBER_MECNUMBER"))
    private List<Collaborator> listMembers;

    @OneToOne
    private TipoEquipa tipoEquipa;

    public Team(final CodigoAlfaNumerico codigoAlfaNumerico, final Acronym acronym, final Designation designation, TipoEquipa teamType,
                final List<Collaborator> listResponsibles, final List<Collaborator> listMembers) {

        Preconditions.noneNull(codigoAlfaNumerico, acronym, designation);
        this.codigoAlfaNumerico = codigoAlfaNumerico;
        this.acronym = acronym;
        this.designation = designation;
        this.tipoEquipa = teamType;
        this.listResponsibles = listResponsibles;
        this.listMembers = listMembers;
    }

    protected Team() {
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

    @Override
    public String toString() {
        return String.format("Código Alfanumérico: %s - Designação: %s - Acrónimo: %s - Tipo de Equipa } %s",
                this.codigoAlfaNumerico, this.designation, this.acronym, this.tipoEquipa);
    }

    public CodigoAlfaNumerico codigoAlfaNumerico() {
        return identity();
    }

    public List<Collaborator> listMembers(){return this.listMembers;}

    public List<Collaborator> listResponsibles(){
        return this.listResponsibles;
    }

    public void removerMembro(Collaborator c){this.listMembers.remove(c);}

    public void adicionarMembro(Collaborator c){this.listMembers.add(c);}

    @Override
    public CodigoAlfaNumerico identity() {
        return this.codigoAlfaNumerico;
    }

    @Override
    public TeamDTO toDTO() {
        return new TeamDTO(codigoAlfaNumerico, acronym.toString(), designation.toString(),
                tipoEquipa.toDTO());
    }

    public TipoEquipa teamType() {
        return this.tipoEquipa;
    }
}
