package eapli.helpdesk.ticket.dto;

import eapli.framework.representations.dto.DTO;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.servico.dto.ServicoDTO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@DTO
public class TicketDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public Calendar dataLimite;
    public Calendar dataSolicitacao;
    public int prioridade;
    public ServicoDTO serviceDTO;
    public CollaboratorDTO collab;
    public String estado;

    public TicketDTO(Long id, Calendar dataLimite, int prioridade, ServicoDTO serviceDTO, Calendar dataSolicitacao, CollaboratorDTO collab, String estado) {
        this.id = id;
        this.dataLimite = dataLimite;
        this.prioridade = prioridade;
        this.serviceDTO = serviceDTO;
        this.dataSolicitacao = dataSolicitacao;
        this.collab = collab;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TICKET -> " +
                "Id: " + id +
                "- Número Mecanográfico do Colaborador Solicitante: " + collab.mecanographicNumber +
                "- Data de Solicitação: " + dataSolicitacao.getTime() +
                "- Data Limite: " + dataLimite.getTime() +
                "- Prioridade: " + prioridade +
                "- Estado: " + estado +
                "- Serviço Associado [ Nível de Criticidade: " + serviceDTO.critDTO.valor +
                "- Código: " + serviceDTO.code +
                "- Descrição Breve: " + serviceDTO.briefDescription +
                "- Descrição Detalhada: " + serviceDTO.detailedDescription + "]";
    }
}
