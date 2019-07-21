package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MiembrosEquipoEmpresas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the MiembrosEquipoEmpresas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MiembrosEquipoEmpresasRepository extends JpaRepository<MiembrosEquipoEmpresas, Long>, JpaSpecificationExecutor<MiembrosEquipoEmpresas> {

    @Query("select miembrosEquipoEmpresas from MiembrosEquipoEmpresas miembrosEquipoEmpresas where miembrosEquipoEmpresas.miembro.login = ?#{principal.preferredUsername}")
    List<MiembrosEquipoEmpresas> findByMiembroIsCurrentUser();

}
