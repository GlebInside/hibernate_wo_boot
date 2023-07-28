package com.example.hibernate_wo_boot.dao.daoImpl;

import com.example.hibernate_wo_boot.dao.AbstractDao;
import com.example.hibernate_wo_boot.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Long, Employee> {

    @Override
    public Employee findById(Long id) {
        val employee = getByKey(id);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> contactRoot = criteria.from(Employee.class);
        criteria.select(contactRoot);
        return getSession().createQuery(criteria).getResultList();
    }

    @Override
    public void merge(Long id, Employee e) {
        Employee current = findById(id);
        e.setEmployeeId(current.getEmployeeId());
        getSession().merge(e);
    }

}
