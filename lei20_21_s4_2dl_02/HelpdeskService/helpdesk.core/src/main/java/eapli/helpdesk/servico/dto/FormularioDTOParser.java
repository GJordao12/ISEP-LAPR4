package eapli.helpdesk.servico.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.servico.domain.Formulario;
import eapli.helpdesk.servico.repositories.FormularioRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class FormularioDTOParser implements DTOParser<FormularioDTO, Formulario> {

    private final FormularioRepository formRepository;

    public FormularioDTOParser(final FormularioRepository formRepository) {
        this.formRepository = formRepository;
    }

    public FormularioDTOParser() {
        this.formRepository = PersistenceContext.repositories().formulario();
    }

    @Override
    public Formulario valueOf(FormularioDTO dto) {
        Optional<Formulario> form = Optional.empty();
        try {
            form = formRepository.ofIdentity(CodigoAlfaNumerico.valueOf(dto.codigo));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        if (!form.isPresent())
            throw new IllegalArgumentException(String.format("No form found with code: %s", dto.codigo));

        return form.get();
    }
}
