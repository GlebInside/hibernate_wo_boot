package com.example.hibernate_wo_boot.employee;

import com.example.hibernate_wo_boot.AbstractDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Long, Employee> {

    public Employee findById(Long id) {
        val employee = getByKey(id);
        return employee;
    }

    public List<Employee> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> contactRoot = criteria.from(Employee.class);
        criteria.select(contactRoot);
        return getSession().createQuery(criteria).getResultList();
    }

    public void save(Employee employee) {
        persist(employee);
    }

}
