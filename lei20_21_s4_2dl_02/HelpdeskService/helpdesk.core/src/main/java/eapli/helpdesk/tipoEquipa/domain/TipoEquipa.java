/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.helpdesk.tipoEquipa.domain;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import com.fasterxml.jackson.annotation.JsonProperty;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;

import eapli.helpdesk.equipa.dto.TeamTypeDTO;

import java.io.Serializable;


@XmlRootElement
@Entity
public class TipoEquipa implements AggregateRoot<CodigoAlfaNumerico>, DTOable<TeamTypeDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    // business ID
    @XmlElement
    @JsonProperty
    @EmbeddedId
    private CodigoAlfaNumerico codAlfaNum;

    @XmlElement
    @JsonProperty
    private Descricao descricao;

    @XmlElement
    @JsonProperty
    private Cor cor;

    public TipoEquipa() {
        // for ORM
    }


    public TipoEquipa(final Cor cor, final CodigoAlfaNumerico codAlfaNum,final Descricao descricao) {
        if (cor == null ) {
            throw new IllegalArgumentException();
        }
        this.cor=cor;

        if (codAlfaNum == null ) {
            throw new IllegalArgumentException();
        }
        this.codAlfaNum = codAlfaNum;

        if (descricao == null ) {
            throw new IllegalArgumentException();
        }
        this.descricao = descricao;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String toString() {
        return String.format("Código Alfanumérico: %s - Descrição: %s - Cor: %s", this.codAlfaNum, this.descricao, this.cor);
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
        return this.codAlfaNum;
    }

    public TeamTypeDTO toDTO() {
        return new TeamTypeDTO(this.cor.toString(),this.codAlfaNum.toString(),
                this.descricao.toString());
    }

    public Cor cor(){
        return this.cor;
    }

    public Descricao descricao(){
        return this.descricao;
    }

    public void changeCodTo(CodigoAlfaNumerico cod){
        this.codAlfaNum=cod;
    }

    public void changeDescricaoTo(Descricao descricao){this.descricao=descricao;}

    public void changeCorTo(Cor cor){this.cor=cor;}
}