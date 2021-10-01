
package eapli.helpdesk.catalogo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.criticidade.domain.Criticidade;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.*;


/**
 * O Catálogo.
 */


@XmlRootElement
@Entity
public class Catalogo implements AggregateRoot<Id>, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @EmbeddedId
    private Id id;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private Titulo titulo;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private DescricaoBreve descBreve;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private  DescricaoCompleta descCompleta;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private  Criticidade criticidade;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private  Collaborator colabRes;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Team> equipas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Servico> servicos = new ArrayList<>();

    @JsonIgnore
    @XmlTransient
    @Lob
    private byte[] image;

    protected Catalogo() {
        // for ORM
    }

    public Catalogo(final Id id, final DescricaoBreve descBreve, final DescricaoCompleta descCompleta, final Titulo titulo, final byte[] image, Collaborator colabRes, Criticidade criticidade, List<Team> equipas,List<Servico> servicos) {
        this.id = id;
        this.descBreve = descBreve;
        this.descCompleta = descCompleta;
        this.titulo = titulo;
        this.colabRes = colabRes;
        this.criticidade = criticidade;
        this.equipas = equipas;
        this.servicos =servicos;
        Preconditions.nonNull(image);

        changeImage(image);
    }

    public byte[] image() {
        // defensive copy
        return Arrays.copyOf(image, image.length);
    }

    public Id getId() {
        return id;
    }

    public void changeImage(final byte[] image) {
        // defensive copy
        this.image = Arrays.copyOf(image, image.length);
    }

    public List<Team> equipas(){
        return this.equipas;
    }

    public List<Servico> servicos(){
        return this.servicos;
    }

    public DescricaoBreve getDescBreve() {
        return descBreve;
    }

    public DescricaoCompleta getDescCompleta() {
        return descCompleta;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public boolean sameAs(final Object other) {
        final Catalogo cat = (Catalogo) other;
        return this.equals(cat) && id.equals(cat.getId());
    }

    public Criticidade getCriticidade() {
        return criticidade;
    }

    public Collaborator getColabRes() {
        return colabRes;
    }

    public List<Team> getEquipas() {
        return equipas;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalogo)) return false;
        Catalogo catalogo = (Catalogo) o;
        return getId().equals(catalogo.getId()) && getTitulo().equals(catalogo.getTitulo()) && getDescBreve().equals(catalogo.getDescBreve()) && getDescCompleta().equals(catalogo.getDescCompleta()) && getCriticidade().equals(catalogo.getCriticidade()) && getColabRes().equals(catalogo.getColabRes()) && getEquipas().equals(catalogo.getEquipas()) && servicos().equals(catalogo.servicos()) && Arrays.equals(getImage(), catalogo.getImage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getTitulo(), getDescBreve(), getDescCompleta(), getCriticidade(), getColabRes(), getEquipas(), servicos());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }

    public Id identity() {
        return this.id;
    }

    public void changeListaDeServicosTo(List<Servico>newServicos){
        this.servicos=newServicos;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", titulo=" + titulo +
                ", descBreve=" + descBreve +
                ", descCompleta=" + descCompleta +
                ", criticidade=" + criticidade +
                '}';
    }
}
