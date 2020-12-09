package dao;

import entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImp implements SingerDao {

    private static final Log logger = LogFactory.getLog(SingerDaoImp.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SingerDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum() {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("Singer.FindAllWithAlbums").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return (Singer) sessionFactory.getCurrentSession().
                createNamedQuery("Singer.FindById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(Singer contact) {
        throw new NotImplementedException("save");
    }

    @Override
    public void delete(Singer contact) {
        throw new NotImplementedException("delete");
    }
}
