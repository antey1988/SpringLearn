package ch8.services;

import ch8.entities.SingerAudit;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("dataJpaSingerAuditHibernateService")
@Transactional
public class SingerAuditHibernateServiceImpl extends SingerAuditServiceImpl implements SingerAuditHibernateService{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(em);
        return auditReader.find(SingerAudit.class, id, revision);
    }
}
