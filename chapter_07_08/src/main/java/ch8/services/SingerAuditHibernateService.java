package ch8.services;

import ch8.entities.SingerAudit;

public interface SingerAuditHibernateService extends SingerAuditService {

    //    Hibernate Control Version
    SingerAudit findByRevision(Long id, int revision);
}
