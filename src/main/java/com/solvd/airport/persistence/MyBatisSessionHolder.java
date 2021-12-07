package com.solvd.airport.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSessionHolder {

    private static final String CONFIG_FILE_NAME = "mybatis-config.xml";
    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        SQL_SESSION_FACTORY = buildSessionFactory();
    }

    private static SqlSessionFactory buildSessionFactory() {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(CONFIG_FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException("Unable read config file", e);
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION_FACTORY;
    }
}
