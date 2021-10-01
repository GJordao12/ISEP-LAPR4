package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.ticket.application.AddTicketController;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.exceptions.FormularioPreenchidoException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TicketBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ServicoBootstrapper.class);
    final CollaboratorRepository collaboratorRepository= PersistenceContext.repositories().collaborators();
    final ServicoRepository servicoRepository=PersistenceContext.repositories().servico();
    final AtividadeTicketRepository atividadeTicketRepository=PersistenceContext.repositories().atividadesTicket();

    @Override
    public boolean execute() {
        Optional<Servico>servico1=null;
        try {
            servico1=servicoRepository.ofIdentity(new CodigoAlfaNumerico("epa01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab= null;
        try {
            colab = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list=new ArrayList<>();
        for(AtributoFormulario a1:servico1.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("Tipo de ausência")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"Férias");
                list.add(a2);
            }
            if(a1.nome().toString().equals("Justificação")){
                AtributoPreenchido a3=new AtributoPreenchido(a1,"Férias");
                list.add(a3);
            }
            if(a1.nome().toString().equals("Periodo de ausência(data de inicio)")){
                AtributoPreenchido a4=new AtributoPreenchido(a1,new Date(22/9/2021));
                list.add(a4);
            }
            if(a1.nome().toString().equals("Periodo de ausência(data de fim)")){
                AtributoPreenchido a5=new AtributoPreenchido(a1,new Date(27/9/2021));
                list.add(a5);
            }
        }
        FormularioPreenchido form = new FormularioPreenchido(servico1.stream().iterator().next().formularioSolicitacao(),list);
        Calendar c = Calendar.getInstance();
        c.set(2022, Calendar.OCTOBER, 20);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Ticket ticket=null;
        try {
            ticket=registerTicket(servico1.stream().iterator().next(), c, 1, form,colab.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }
        Optional<Collaborator> colab2= null;
        try {
            colab2 = collaboratorRepository.ofIdentity(new MecanographicNumber("11809"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }
        Calendar c2=Calendar.getInstance();
        AtividadeTicket atividadeTicket=new AtividadeTicket(ticket,colab2.stream().iterator().next(),"APROVACAO",null,null,c2);
        AtividadeTicket atividadeTicket2=new AtividadeTicket(ticket,colab2.stream().iterator().next(),"MANUAL",null,null,c2);
        registerAtividadeTicket(atividadeTicket);
        registerAtividadeTicket(atividadeTicket2);



        Optional<Servico>servico2=null;
        try {
            servico2=servicoRepository.ofIdentity(new CodigoAlfaNumerico("mnt01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab3= null;
        try {
            colab3 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list2=new ArrayList<>();
        for(AtributoFormulario a1:servico2.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("Numero de telefone")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,910578983);
                list2.add(a2);
            }
        }
        FormularioPreenchido form2 = new FormularioPreenchido(servico2.stream().iterator().next().formularioSolicitacao(),list2);
        Calendar c3 = Calendar.getInstance();
        c3.set(2021, Calendar.SEPTEMBER, 26);
        c3.set(Calendar.HOUR_OF_DAY, 0);
        c3.set(Calendar.MINUTE, 0);
        c3.set(Calendar.SECOND, 0);
        c3.set(Calendar.MILLISECOND, 0);
        Ticket ticket2=null;
        try {
            ticket2=registerTicket(servico2.stream().iterator().next(), c3, 1, form2,colab3.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }

        AtividadeTicket atividadeTicket3=new AtividadeTicket(ticket2,null,"AUTOMATICA",null,null,null);
        registerAtividadeTicket(atividadeTicket3);


        Optional<Servico>servico3=null;
        try {
            servico3=servicoRepository.ofIdentity(new CodigoAlfaNumerico("ad01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab5= null;
        try {
            colab5 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list3=new ArrayList<>();
        for(AtributoFormulario a1:servico3.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("Tipo de desconto")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"metade de preço");
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Desconto")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,10);
                list3.add(a2);
            }
            if (a1.nome().toString().equals("ID")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,2);
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Valor de Desconto")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,15);
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Recorrência")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"15 dias");
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Fundamentação do pedido")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"Saber preço do produto");
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Identificação da Fatura")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,12344);
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Código Interno Cliente")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"34rfr");
                list3.add(a2);
            }
            if (a1.nome().toString().equals("Nome")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"34rfr");
                list3.add(a2);
            }
        }
        FormularioPreenchido form3 = new FormularioPreenchido(servico3.stream().iterator().next().formularioSolicitacao(),list3);
        Calendar c5 = Calendar.getInstance();
        c5.set(2021, Calendar.NOVEMBER, 29);
        c5.set(Calendar.HOUR_OF_DAY, 0);
        c5.set(Calendar.MINUTE, 0);
        c5.set(Calendar.SECOND, 0);
        c5.set(Calendar.MILLISECOND, 0);
        Ticket ticket3=null;
        try {
            ticket3=registerTicket(servico3.stream().iterator().next(), c3, 1, form3,colab5.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }
        Optional<Collaborator> colab6= null;
        try {
            colab6 = collaboratorRepository.ofIdentity(new MecanographicNumber("11904"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }
        Calendar c6=Calendar.getInstance();
        AtividadeTicket atividadeTicket4=new AtividadeTicket(ticket3,colab6.stream().iterator().next(),"APROVACAO",null,null,c6);
        AtividadeTicket atividadeTicket5=new AtividadeTicket(ticket3,null,"AUTOMATICA",null,null,null);
        registerAtividadeTicket(atividadeTicket4);
        registerAtividadeTicket(atividadeTicket5);



        Optional<Servico>servico4=null;
        try {
            servico4=servicoRepository.ofIdentity(new CodigoAlfaNumerico("car01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab7= null;
        try {
            colab7 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list4=new ArrayList<>();
        for(AtributoFormulario a1:servico4.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("Concelho")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"Póvoa de Varzim");
                list4.add(a2);
            }
            if (a1.nome().toString().equals("Distrito")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"Porto");
                list4.add(a2);
            }
        }

        FormularioPreenchido form4 = new FormularioPreenchido(servico4.stream().iterator().next().formularioSolicitacao(),list4);
        Calendar c7 = Calendar.getInstance();
        c7.set(2021, Calendar.DECEMBER, 30);
        Ticket ticket4=null;
        try {
            ticket4=registerTicket(servico4.stream().iterator().next(), c7, 1, form4,colab7.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }
        Optional<Collaborator> colab8= null;
        try {
            colab8 = collaboratorRepository.ofIdentity(new MecanographicNumber("11904"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }
        Calendar c8=Calendar.getInstance();
        AtividadeTicket atividadeTicket6=new AtividadeTicket(ticket4,colab8.stream().iterator().next(),"MANUAL",null,null,c8);
        registerAtividadeTicket(atividadeTicket6);


        Optional<Servico>servico5=null;
        try {
            servico5=servicoRepository.ofIdentity(new CodigoAlfaNumerico("cbma01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab9= null;
        try {
            colab9 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list5=new ArrayList<>();
        for(AtributoFormulario a1:servico5.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("Número Mecanográfico")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,11809);
                list5.add(a2);
            }
            if (a1.nome().toString().equals("Mês")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,"Janeiro");
                list5.add(a2);
            }
        }

        FormularioPreenchido form5 = new FormularioPreenchido(servico5.stream().iterator().next().formularioSolicitacao(),list5);
        Calendar c9 = Calendar.getInstance();
        c9.set(2021, Calendar.JANUARY, 30);
        Ticket ticket5=null;
        try {
            ticket5=registerTicket(servico5.stream().iterator().next(), c9, 1, form5,colab9.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }

        AtividadeTicket atividadeTicket7=new AtividadeTicket(ticket5,null,"AUTOMATICA",null,null,null);
        registerAtividadeTicket(atividadeTicket7);


        Optional<Servico>servico6=null;
        try {
            servico6=servicoRepository.ofIdentity(new CodigoAlfaNumerico("spp01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab10= null;
        try {
            colab10 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list6=new ArrayList<>();
        for(AtributoFormulario a1:servico6.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("ID")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,4);
                list6.add(a2);
            }
        }

        FormularioPreenchido form6 = new FormularioPreenchido(servico6.stream().iterator().next().formularioSolicitacao(),list6);
        Calendar c10 = Calendar.getInstance();
        c9.set(2021, Calendar.JANUARY, 30);
        Ticket ticket6=null;
        try {
            ticket6=registerTicket(servico6.stream().iterator().next(), c10, 1, form6,colab10.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }

        AtividadeTicket atividadeTicket8=new AtividadeTicket(ticket6,null,"AUTOMATICA",null,null,null);
        registerAtividadeTicket(atividadeTicket8);


        Optional<Servico>servico7=null;
        try {
            servico7=servicoRepository.ofIdentity(new CodigoAlfaNumerico("sdsp01"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab11= null;
        try {
            colab11 = collaboratorRepository.ofIdentity(new MecanographicNumber("11907"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        List<AtributoPreenchido> list7=new ArrayList<>();
        for(AtributoFormulario a1:servico7.stream().iterator().next().formularioSolicitacao().atributos()){
            if (a1.nome().toString().equals("ID")){
                AtributoPreenchido a2=new AtributoPreenchido(a1,3);
                list7.add(a2);
            }
        }

        FormularioPreenchido form7 = new FormularioPreenchido(servico7.stream().iterator().next().formularioSolicitacao(),list7);
        Calendar c11 = Calendar.getInstance();
        c9.set(2021, Calendar.JANUARY, 30);
        Ticket ticket7=null;
        try {
            ticket7=registerTicket(servico7.stream().iterator().next(), c11, 1, form7,colab11.stream().iterator().next());
        } catch (FormularioPreenchidoException e) {
            e.printStackTrace();
        }

        AtividadeTicket atividadeTicket9=new AtividadeTicket(ticket7,null,"AUTOMATICA",null,null,null);
        registerAtividadeTicket(atividadeTicket9);
        return false;
    }

    private Ticket registerTicket(final Servico servico, final Calendar data, final int prioridade, final FormularioPreenchido form, final Collaborator colab) throws FormularioPreenchidoException {
        final AddTicketController controller = new AddTicketController();

        return controller.registerTicket(servico, data, prioridade, form,colab);
    }

    private void registerAtividadeTicket(AtividadeTicket atividadeTicket){
        atividadeTicketRepository.save(atividadeTicket);
        // ignoring exception. assuming it is just a primary key violation
        // due to the tentative of inserting a duplicated user
        LOGGER.trace("Assuming existing record");
    }
}
