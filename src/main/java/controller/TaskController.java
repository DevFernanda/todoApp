/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
// import javafx.concurrent.Task;
import util.ConnectionFactory;
import model.task;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Win10
 */
public class TaskController {
    
    public void save (task task) {
        String sql = "INSERT INTO tasks (idProject, name, description,"
                + "completed, notes, deadline,"
                + "createdAt, updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
        Connection connection = null;
        PreparedStatement statement = null;
    
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date (task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex){
            throw new RuntimeException ("Erro ao salvar a tarefa" +
                    ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update (task task) throws SQLException{
        String sql = "UPDATE tasks SET idProject = ?,"
                + "name = ?,"
                + "description = ?,"
                + "notes = ?,"
                + "completed = ?,"
                + "deadline = ?,"
                + "createdAt = ?,"
                + "updateAt = ?"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean (5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate (7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt (9, task.getId());
            statement.execute();
                    
                    
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa" 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void removeById(int taskId){
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection(); // crio conexão
            statement = connection.prepareStatement(sql); // preparo o comando
            statement.setInt(1, taskId); // setar o parametro do id
            statement.execute(); // executa
        } catch (Exception e) {
            throw new RuntimeException ("Erro ao deletar a tarefa"); 
        } finally {
            ConnectionFactory.closeConnection(connection, statement); 
        }
    }
    
    public List<task> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
                
        Connection connection = null;
        PreparedStatement statement = null; 
        ResultSet resultSet = null; 
        
        // lista de tarefas que será devolvida quando a chamada do método acontecer.
        List <task> tasks = new ArrayList<task>();
        
        
        try {
            connection = ConnectionFactory.getConnection();
           
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery(); // devolve o valor de resposta do select 
            // do banco de dados;
            
            while (resultSet.next()){
                task task = new task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updateAt"));
                
                
                tasks.add(task);
                
            }
        } catch (Exception e) {
            throw new RuntimeException ("Erro ao buscar dados" +
                    e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        // Lista de tarefas que foi criada e carregada do banco de dados.
        return tasks;
    }
}
