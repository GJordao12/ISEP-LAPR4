package eapli.helpdesk.atividadeTicket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
//@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="TYPE")
public class AtividadeTicket implements Serializable, AggregateRoot<Long>, DTOable<AtividadeTicketDTO> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    long id;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ticket")
    private Ticket ticket;

    @XmlElement
    @JsonProperty
    @OneToOne
    private Collaborator collaborator;

    @XmlElement
    @JsonProperty
    private String tipoAtividade;

    @XmlElement
    @JsonProperty
    private Calendar dataInicio;

    @XmlElement
    @JsonProperty
    private Calendar dataFim;

    @XmlElement
    @JsonProperty
    @OneToOne
    private FormularioPreenchido formularioPreenchido;

    @XmlElement
    @JsonProperty
    private Calendar dataAtribuicao;

    protected AtividadeTicket() {
        // for ORM
    }

    public AtividadeTicket(Ticket ticket, Collaborator collaborator, String tipoAtividade, Calendar dataInicio, Calendar dataFim, Calendar dataAtribuicao) {
        this.ticket = ticket;
        this.collaborator = collaborator;
        this.tipoAtividade = tipoAtividade;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.formularioPreenchido = null;
        this.dataAtribuicao = dataAtribuicao;
    }

    @Override
    public String toString() {
        if (this.collaborator != null) {
            return String.format("Atividade Ticket: ID->  %d - Colaborador-> %s - Tipo Atividade-> %s - Data Inicio-> %s - Data Fim-> %s", (int) this.id, this.collaborator.longName(), this.tipoAtividade, this.dataInicio.getTime(), this.dataFim.getTime());
        }
        if (this.tipoAtividade.equalsIgnoreCase("automatica")) {
            return String.format("Atividade Ticket: ID->  %d - Tipo Atividade-> %s - Data Inicio-> %s - Data Fim-> %s", (int) this.id, this.tipoAtividade, this.dataInicio.getTime(), this.dataFim.getTime());
        }
        return String.format("Atividade Ticket: ID->  %d - Serviço-> %s - Criticidade-> %s - Data Limite: %s Tipo Atividade-> %s", (int) this.id, this.ticket.servico().descricaoBreve(), this.ticket.servico().criticidade().identity().toString(), this.ticket.dataLimite().getTime(), this.tipoAtividade);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public AtividadeTicketDTO toDTO() {
        return new AtividadeTicketDTO(this.id, this.ticket.toDTO(), this.collaborator.toDTO(), this.tipoAtividade);
    }

    public void atribuirColaborador(Collaborator collab) {
        this.collaborator = collab;
    }

    public String tipoAtividade() {
        return this.tipoAtividade;
    }

    public void defineDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void defineDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public void changeCollaboratorTo(Collaborator colab) {
        this.collaborator = colab;
    }

    public Ticket ticket() {
        return this.ticket;
    }

    public Calendar dataFim() {
        return this.dataFim;
    }

    public Calendar dataAtribuicao() {
        return this.dataAtribuicao;
    }

    public void changeDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public void changeDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtividadeTicket)) return false;
        AtividadeTicket that = (AtividadeTicket) o;
        return id == that.id;
    }

    public void updateDataAtribuicao(Calendar instance) {
        this.dataAtribuicao = instance;
    }
}
