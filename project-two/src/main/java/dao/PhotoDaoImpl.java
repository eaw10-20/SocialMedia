package dao;

import base.model.Photos;
import base.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class PhotoDaoImpl implements PhotoDao{

    @Override
    public void uploadPhoto(Photos photo) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(photo);

        tx.commit();
    }

    @Override
    public Photos getPhotobyId(int id) {
        Session session = HibernateUtil.getSession();

        Photos photo = session
                .createQuery("from User WHERE photo_id = '" + id + "'", Photos.class)
                .uniqueResult();

        return photo;
    }

    @Override
    public void deletePhoto(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.delete(new Photos(id));

        tx.commit();
    }
}
