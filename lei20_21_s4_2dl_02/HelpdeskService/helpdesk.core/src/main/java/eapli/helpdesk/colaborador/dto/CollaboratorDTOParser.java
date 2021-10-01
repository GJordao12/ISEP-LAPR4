package eapli.helpdesk.colaborador.dto;


import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class CollaboratorDTOParser implements DTOParser<CollaboratorDTO, Collaborator> {

    private final CollaboratorRepository repo = PersistenceContext.repositories().collaborators();

    public CollaboratorDTOParser() {
        // Empty
    }

    @Override
    public Collaborator valueOf(final CollaboratorDTO dto) {
        Optional<Collaborator> collab = repo.findByMecanographicNumber(dto.mecanographicNumber);

        if (!collab.isPresent())
            throw new IllegalArgumentException(
                    String.format("There is no Collaborator with Mecanographic number: %s", dto.mecanographicNumber));

        return collab.get();
    }
}
