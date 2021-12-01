package com.solvd.airport.persistence;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRepository {

    void create(@Param("employee") Employee employee, @Param("positionId") Long positionId, @Param("airlineId") Long airlineId) throws InsertException;

}
