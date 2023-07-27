package com.example.hibernate_wo_boot.project;

import com.example.hibernate_wo_boot.AbstractDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Long, Project> {

    public Project findById(Long id) {
        val project = getByKey(id);
        return project;
    }

    public List<Project> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        Root<Project> contactRoot = criteria.from(Project.class);
        criteria.select(contactRoot);
        return getSession().createQuery(criteria).getResultList();
    }

    public void save(Project project) {
        persist(project);
    }

    public Project findByName(String name) {
        val query = getSession().createQuery("from Project where name = :name");
        query.setParameter("name", name);
        return (Project) query.getSingleResult();
    }

}

