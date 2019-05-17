package models;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Zeth
 */
public class Conexion {
    public DriverManagerDataSource conectar(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/nutricoach");
        dataSource.setUsername("root");
        dataSource.setPassword("walter22");
        return dataSource;
    }
}
