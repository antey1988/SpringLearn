package ch8.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("singerSummaryIntype")
@Repository
@Transactional
public class SingerSummaryUntypeImpl {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public void displayAllSingerSummary() {
        List result = em.createQuery("select s.firstName, s.lastName, " +
                "a.title from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate = " +
                "(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)")
                .getResultList();
        AtomicInteger count = new AtomicInteger();
        result.forEach(r-> {
            Object[] values = (Object[]) r;
            System.out.println(count.incrementAndGet() + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        });
    }
}
