package ch8.services;

import ch8.entities.SingerAudit;
import ch8.repos.SingerAuditRepository;
import com.google.common.collect.Lists;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("dataJpaSingerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService{

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findById(id).get();
    }

    @Override
    public SingerAudit save(SingerAudit singer) {
        return singerAuditRepository.save(singer);
    }
}
