package com.jpa.dao.impl;

import com.jpa.dao.UserDB;
import com.jpa.entity.UserEntity;
import com.jpa.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDBImpl implements UserDB {

    @Override
    public UserEntity getUserById(long userId) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager(); // Tạo một EntityManager mới
        try {
            UserEntity user = em.find(UserEntity.class, userId); // Tìm user theo userId
            return user; // Trả về user tìm thấy
        } finally {
            em.close(); // Đảm bảo rằng EntityManager được đóng sau khi sử dụng
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager(); // Tạo một EntityManager mới
        String qString = "SELECT u FROM UserEntity u " + // Đổi user thành UserEntity
                "WHERE u.email = :email";
        TypedQuery<UserEntity> q = em.createQuery(qString, UserEntity.class); // Sửa User.class thành UserEntity.class
        q.setParameter("email", email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Long insert(UserEntity user) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long userId = null;  // Biến để lưu id của user vừa được thêm

        trans.begin();
        try {
            em.persist(user);  // Thực hiện thêm user
            trans.commit();    // Commit giao dịch

            userId = user.getId();  // Lấy id của user sau khi thêm thành công
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();  // Rollback nếu có lỗi
        } finally {
            em.close();  // Đóng EntityManager
        }

        return userId;  // Trả về id của user
    }

}

