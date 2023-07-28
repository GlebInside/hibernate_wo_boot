package com.example.hibernate_wo_boot.dao.daoImpl;

import com.example.hibernate_wo_boot.dao.AbstractDao;
import com.example.hibernate_wo_boot.dao.ProjectDao;
import com.example.hibernate_wo_boot.model.Project;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Long, Project> implements ProjectDao {

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

    public void merge(Long id, Project e) {
        Project current = findById(id);
        e.setProjectId(current.getProjectId());
        getSession().merge(e);
    }

    public Project findByName(String name) {
        val query = getSession().createQuery("from Project where name = :name", Project.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}

