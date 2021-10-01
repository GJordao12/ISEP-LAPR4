package eapli.helpdesk.atividadeTicket.service;

import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.dto.CriticidadeDTO;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.servico.dto.ServicoDTO;
import eapli.helpdesk.ticket.dto.TicketDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de Testes dos métodos de filtragem e ordenação utilizados nas US´s 3011 e 3021
 */
class ListAtividadeTicketServiceTest {

    ListAtividadeTicketService ls = new ListAtividadeTicketService();

    @Test
    void orderByPriority() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("20/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(2))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = ls.orderByPriority(list);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at2);
        ListObj.add(at1);
        Assertions.assertEquals(ListObj, finalList);
    }

    @Test
    void orderByCriticality() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("20/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(3))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = ls.orderByCriticality(list);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at2);
        ListObj.add(at1);
        Assertions.assertEquals(ListObj, finalList);
    }

    @Test
    void orderByDeadline() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("22/12/2001"));
        Calendar c1 = Calendar.getInstance();
        c1.setTime(formatoData.parse("20/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c1,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(2))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = ls.orderByDeadline(list);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at2);
        ListObj.add(at1);
        Assertions.assertEquals(ListObj, finalList);
    }

    @Test
    void filterByPriority() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("22/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(2))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = (List<AtividadeTicketDTO>) ls.filterByPriority(list, 3);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at1);
        Assertions.assertEquals(ListObj, finalList);
    }

    @Test
    void filterByCriticality() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("22/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(2))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = (List<AtividadeTicketDTO>) ls.filterByCriticality(list, 2);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at2);
        Assertions.assertEquals(ListObj, finalList);
    }

    @Test
    void filterByDeadline() throws ParseException, ValorException {
        List<String> keywords = new ArrayList<>();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse("22/12/2001"));
        AtividadeTicketDTO at1 = new AtividadeTicketDTO(3, new TicketDTO(4L, c, 3, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(1))), c, null, "Submetido"), null, null);
        AtividadeTicketDTO at2 = new AtividadeTicketDTO(4, new TicketDTO(5L,c,1, new ServicoDTO("a123", "titulo", "bf", "bfdescription", "feddback", keywords, null, null, new CriticidadeDTO(new Valor(2))), c, null, "Submetido"), null, null);
        List<AtividadeTicketDTO> list = new ArrayList<>();
        list.add(at1);
        list.add(at2);
        List<AtividadeTicketDTO> finalList = (List<AtividadeTicketDTO>) ls.filterByDeadline(list, c);
        List<AtividadeTicketDTO> ListObj = new ArrayList<>();
        ListObj.add(at1);
        ListObj.add(at2);
        Assertions.assertEquals(ListObj, finalList);
    }
}