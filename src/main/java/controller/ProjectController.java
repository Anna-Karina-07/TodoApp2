/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author L?ssia Oliveira
 */
public class ProjectController {
    
    public void save(Project project) {
        
        String sql = "INSERT INTO projects(name,"
                + "description,"
                + "createdAt,"
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Cria uma conex?o com o banco
            connection = ConnectionFactory.getConnection();
            // Cria um PreparedStatement, classe usada para exucutar a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            //statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            
            if (project.getUpdatedAt() == null ) {
                statement.setDate(4, new Date(project.getCreatedAt().getTime()));
            } else {
                statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            }
                            
            
            // Executaa a sql para inser??o dos dados
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    
    public void update(Project project) throws ClassNotFoundException {
        
        String sql = "UPDATE projects SET "
                + "name = ?,"
                + "description = ?,"
                + "createdAt = ?"
                + "updatedAt = ?,"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Cria uma conex?o com o banco
            connection = ConnectionFactory.getConnection();
            // Cria uma PreparedStatement, classe usada para a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            // Executando a slq para inser??o dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public List<Project> getAll() throws SQLException {
    
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        // Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
        
        while (resultSet.next()){
            
            Project project = new Project();
            
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("description"));
            project.setCreatedAt(resultSet.getDate("createdAt"));
            project.setUpdatedAt(resultSet.getDate("updatedAt"));
            
            // Adiciono o contato recuperado, a lista de contatos
            projects.add(project);
        }
    } catch (Exception ex) {
        throw new RuntimeException("Erro ao buscar os projetos", ex);
    } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return projects;
    }
    
    public void removeById(int idProject) {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

}
