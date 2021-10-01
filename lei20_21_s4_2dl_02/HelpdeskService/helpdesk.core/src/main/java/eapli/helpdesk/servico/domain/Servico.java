
package eapli.helpdesk.servico.domain;

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

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.helpdesk.servico.dto.ServicoDTO;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.catalogo.domain.DescricaoBreve;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Criticidade;

import java.io.Serializable;
import java.util.*;

@XmlRootElement
@Entity
public class Servico implements AggregateRoot<CodigoAlfaNumerico>, DTOable<ServicoDTO>, Serializable {

    private static final long serialVersionUID = 1L;
    // business ID
    @XmlElement
    @JsonProperty
    @EmbeddedId
    private CodigoAlfaNumerico codAlfaNum;

    @XmlElement
    @JsonProperty
    private DescricaoBreve descricaoBreve;

    @XmlElement
    @JsonProperty
    private DescricaoCompletaServico descricaoCompletaServico;

    @XmlElement
    @JsonProperty
    private String estadoServico;

    @XmlElement
    @JsonProperty
    private String feedback;

    @XmlElement
    @JsonProperty
    private Integer tempoLimiteFeedback;

    @XmlElement
    @JsonProperty
    private Titulo titulo;

    @JsonIgnore
    @XmlTransient
    @Lob
    private byte[] icone;

    @JsonProperty
    @ElementCollection
    private Set<String> keywords = new HashSet<>();

    @OneToOne
    private Criticidade criticidade;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "formularioSolicitacaoId")
    private Formulario formularioDeSolicitacao;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "workflowId")
    private Workflow workflow;

    protected Servico() {
        // for ORM
    }


    public Servico(CodigoAlfaNumerico codAlfaNum,DescricaoBreve descricaoBreve,DescricaoCompletaServico descricaoCompletaServico,
                   String estadoServico,String feedback,Integer tempoLimiteFeedback,Titulo titulo,byte[] icone,
                   Set<String> keywords,Formulario formularioDeSolicitacao,Criticidade criticidade,Workflow workflow) {
        this.codAlfaNum = codAlfaNum;
        this.descricaoCompletaServico=descricaoCompletaServico;
        this.estadoServico=estadoServico;
        this.descricaoBreve=descricaoBreve;
        this.feedback=feedback;
        this.tempoLimiteFeedback=tempoLimiteFeedback;
        this.keywords=keywords;
        this.titulo=titulo;
        this.criticidade=criticidade;
        if(icone!=null) {
            changeIcone(icone);
        }
        this.formularioDeSolicitacao=formularioDeSolicitacao;
        this.workflow=workflow;

    }

    public byte[] icone() {
        // defensive copy
        return Arrays.copyOf(icone, icone.length);
    }

    public void changeIcone(final byte[] icone) {
        // defensive copy
        this.icone = Arrays.copyOf(icone, icone.length);
    }

    public byte[]icone2(){
        return this.icone;
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

    public DescricaoBreve descricaoBreve(){return this.descricaoBreve;}

    public DescricaoCompletaServico descricaoCompleta(){return this.descricaoCompletaServico;}

    public Titulo titulo(){return this.titulo;}

    public String estado(){return this.estadoServico;}

    public String feedback(){return this.feedback;}

    public Integer tempoLimiteFeedback()
    {
        return this.tempoLimiteFeedback;
    }

    public Formulario formularioSolicitacao(){
        return this.formularioDeSolicitacao;
    }

    public Set<String> keywords(){return this.keywords;}

    public Criticidade criticidade(){return this.criticidade;}

    public Workflow workflow(){return this.workflow;}

    public void changeKeywords(Set<String> newKeywords){
        this.keywords=newKeywords;
    }

    public void changeDescricaoCompletaTo(DescricaoCompletaServico newDescricaoCompleta){
        this.descricaoCompletaServico=newDescricaoCompleta;
    }

    public void changeDescricaoBreveTo(DescricaoBreve newDescricaoBreve){
        this.descricaoBreve=newDescricaoBreve;
    }

    public void changeTituloTo(Titulo newTitulo){
        this.titulo=newTitulo;
    }

    public void changeFeedbackTo(String newFeedback){
        this.feedback=newFeedback;
    }

    public void changeTempoLimiteFeedback(Integer newTempoLimite){
        this.tempoLimiteFeedback=newTempoLimite;
    }

    public void changeFormularioSolicitacaoTo(Formulario newFormularioSolicitacao){
        this.formularioDeSolicitacao=newFormularioSolicitacao;
    }
    public void changeCriticidadeTo(Criticidade criticidade){
        this.criticidade=criticidade;
    }

    public void changeWorkflowTo(Workflow workflow){
        this.workflow=workflow;
    }

    public void changeEstadoServicoTo(String estado){this.estadoServico=estado;}

    @Override
    public CodigoAlfaNumerico identity() {
        return this.codAlfaNum;
    }

    public boolean hasImage() {
        return icone != null && icone.length != 0;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "codAlfaNum=" + codAlfaNum +
                ", descricaoBreve=" + descricaoBreve +
                ", descricaoCompletaServico=" + descricaoCompletaServico +
                ", feedback='" + feedback + '\'' +
                ", tempoLimiteFeedback=" + tempoLimiteFeedback +
                ", titulo=" + titulo +
                ", criticidade=" + criticidade +
                '}';
    }

    @Override
    public ServicoDTO toDTO() {
        List<String> keywordsStringList = new ArrayList<>(keywords);

        return new ServicoDTO(codAlfaNum.toString(), titulo.toString(), descricaoBreve.toString(),
                descricaoCompletaServico.toString(), feedback, keywordsStringList, icone,
                formularioDeSolicitacao != null ? formularioDeSolicitacao.toDTO() : null, criticidade.toDTO());
    }
}
