package eapli.helpdesk.servico.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.servico.domain.Formulario;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

public interface FormularioRepository extends DomainRepository<CodigoAlfaNumerico, Formulario> {

    Formulario findFormularioByCod(CodigoAlfaNumerico cod);
}
