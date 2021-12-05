package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.EmployeeRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class EmployeeMapperImpl implements EmployeeRepository {

    @Override
    public void create(Employee employee, Long positionId, Long airlineId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(EmployeeRepository.class).create(employee, positionId, airlineId);
        }
    }
}
