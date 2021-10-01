
package eapli.helpdesk.colaborador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement
@Entity
public class Collaborator implements AggregateRoot<MecanographicNumber>, DTOable<CollaboratorDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    @XmlElement
    @JsonProperty
    private ShortName shortName;

    @XmlElement
    @JsonProperty
    private LongName longName;

    @XmlElement
    @JsonProperty
    private Calendar birthDate;

    @XmlElement
    @JsonProperty
    private Address address;

    @XmlElement
    @JsonProperty
    private PhoneNumber phoneNumber;

    @JsonProperty
    @OneToOne
    private RoleCollaborator roleCollaborator;

    @OneToOne
    private Collaborator collaborator;


/**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */

    @OneToOne
    private SystemUser systemUser;

    public Collaborator(final SystemUser user, final MecanographicNumber mecanographicNumber, ShortName shortName,
                        LongName longName, Calendar birthDate, Address address, PhoneNumber phoneNumber,
                        RoleCollaborator roleCollaborator, Collaborator colab) {

        Preconditions.noneNull(mecanographicNumber, user, shortName, longName, birthDate, address,
                phoneNumber);
            this.systemUser = user;
            this.mecanographicNumber = mecanographicNumber;
            this.shortName = shortName;
            this.longName = longName;
            this.birthDate = birthDate;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.roleCollaborator = roleCollaborator;
            this.collaborator = colab;
    }

    protected Collaborator() {
        // for ORM only
    }

    public void newPhoneNumber(PhoneNumber phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public Address address(){
        return this.address;
    }

    public String longName(){
        return this.longName.toString();
    }

    public SystemUser user() {
            return this.systemUser;
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

    public MecanographicNumber mecanographicNumber() {
            return identity();
        }

    @Override
    public MecanographicNumber identity() {
            return this.mecanographicNumber;
        }

    @Override
    public String toString() {
        return String.format("Username: %s - Email: %s - %s - %s", this.systemUser.username(), this.systemUser.email(), this.shortName, this.mecanographicNumber);
    }

    @Override
    public CollaboratorDTO toDTO() {

        return new CollaboratorDTO(this.mecanographicNumber, this.longName.toString(),
                this.shortName.toString(), this.address.distrito(), this.address.concelho(),
                this.phoneNumber.toString(),
                this.birthDate.toString(), this.roleCollaborator.toDTO());
    }
}

