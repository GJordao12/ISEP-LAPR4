package eapli.helpdesk.colaborador.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.colaborador.domain.RoleCollaborator;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.funcao.RoleRepository;

import java.util.Optional;

public class CollaboratorRoleDTOParser implements DTOParser<CollaboratorRoleDTO, RoleCollaborator> {

    private final RoleRepository repo = PersistenceContext.repositories().roles();

    public CollaboratorRoleDTOParser() {
        // Empty
    }

    @Override
    public RoleCollaborator valueOf(CollaboratorRoleDTO dto) {
        Optional<RoleCollaborator> collabRole = repo.ofIdentity(dto.codigoAlfaNumerico);

        if (!collabRole.isPresent())
            throw new IllegalArgumentException(String.format("There is no Collaborator role with id: %s", dto.codigoAlfaNumerico));

        return collabRole.get();
    }

}
