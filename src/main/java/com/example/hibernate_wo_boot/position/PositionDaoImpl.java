package com.example.hibernate_wo_boot.position;

import com.example.hibernate_wo_boot.AbstractDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("positionDao")
public class PositionDaoImpl extends AbstractDao<Long, Position> {

    public Position findById(Long id) {
        val position = getByKey(id);
        return position;
    }

    public List<Position> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
        Root<Position> contactRoot = criteria.from(Position.class);
        criteria.select(contactRoot);
        List<Position> positions = getSession().createQuery(criteria).getResultList();
        return positions;
    }

    public void save(Position position) {
        persist(position);
    }

    public Position findByName(String name) {
        val query = getSession().createQuery("from Position where name = :name");
        query.setParameter("name", name);
        return (Position) query.getSingleResult();
    }
}
