package eapli.helpdesk.ticket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.ticket.dto.TicketDTO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@Entity
public class Ticket implements AggregateRoot<Long>, DTOable<TicketDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @OneToOne
    private Servico Servico;

    @OneToOne
    private Collaborator colab;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "formularioPreenchido")
    private FormularioPreenchido form;

    @XmlElement
    @JsonProperty
    private final Calendar dataLimite;

    @XmlElement
    @JsonProperty
    private final int prioridade;

    @XmlElement
    @JsonProperty
    private String estado;

    @XmlElement
    @JsonProperty
    private final Calendar dataSolicitacao;


    public Ticket(Servico servico, Calendar dataLimite, int prioridade, FormularioPreenchido form, Collaborator colab) {
        Servico = servico;
        this.dataLimite = dataLimite;
        this.prioridade = prioridade;
        this.form = form;
        this.estado = "Submetido";
        this.dataSolicitacao = Calendar.getInstance();
        this.colab = colab;
    }

    public Ticket() {
        dataLimite = null;
        prioridade = 0;
        estado = "Submetido";
        this.dataSolicitacao = Calendar.getInstance();
        this.colab = null;
    }

    public String estado() {
        return estado;
    }

    public void changeEstado(String estado){
        this.estado = estado;
    }

    public Servico servico(){
        return this.Servico;
    }

    public Calendar dataLimite() {
        return this.dataLimite;
    }

    public Calendar dataSolicitacao() {
        return dataSolicitacao;
    }

    public Collaborator collaborator() {
        return colab;
    }

    public FormularioPreenchido formularioPreenchido(){return form;}

    @Override
    public TicketDTO toDTO() {
        return new TicketDTO(this.codigo, this.dataLimite, this.prioridade, this.Servico.toDTO(), this.dataSolicitacao, this.colab.toDTO(), this.estado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return prioridade == ticket.prioridade && codigo.equals(ticket.codigo) && Servico.equals(ticket.Servico) && colab.equals(ticket.colab) && form.equals(ticket.form) && dataLimite.equals(ticket.dataLimite) && estado.equals(ticket.estado) && dataSolicitacao.equals(ticket.dataSolicitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, Servico, colab, form, dataLimite, prioridade, estado, dataSolicitacao);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "codigo=" + codigo +
                ", Servico=" + Servico +
                ", colab=" + colab +
                ", form=" + form +
                ", dataLimite=" + dataLimite.getTime() +
                ", prioridade=" + prioridade +
                ", estado='" + estado + '\'' +
                ", dataSolicitacao=" + dataSolicitacao.getTime() +
                '}';
    }

    @Override
    public boolean sameAs(Object other) {
        final Ticket ticket = (Ticket) other;
        return this.equals(ticket) && codigo.equals(ticket.identity());
    }

    @Override
    public Long identity() {
        return codigo;
    }
}
