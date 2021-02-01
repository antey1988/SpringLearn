package services;

import com.google.common.collect.Lists;
import entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import repos.SingerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerServiceTransTemp")
@Repository
public class SingerServiceImplTransTemp implements SingerService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer singer) {
        return null;
    }

    @Override
    public long countAll() {
        return transactionTemplate.execute(
                transactionStatus -> entityManager.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult()
        );
    }
}
