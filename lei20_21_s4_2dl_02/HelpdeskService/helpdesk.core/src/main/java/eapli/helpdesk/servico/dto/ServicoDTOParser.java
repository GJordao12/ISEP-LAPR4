package eapli.helpdesk.servico.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class ServicoDTOParser implements DTOParser<ServicoDTO, Servico> {

    private final ServicoRepository serviceRepository;

    public ServicoDTOParser() {
        this.serviceRepository = PersistenceContext.repositories().servico();
    }

    @Override
    public Servico valueOf(ServicoDTO dto) {
        Optional<Servico> service = Optional.empty();
        try {
            service = serviceRepository.ofIdentity(CodigoAlfaNumerico.valueOf(dto.code));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }
        if (!service.isPresent()) {
            throw new IllegalArgumentException(String.format("No service found with code %s", dto.code));
        }
        return service.get();
    }
}
