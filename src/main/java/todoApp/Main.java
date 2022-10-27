package todoApp;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Project;
import model.task;
import util.ConnectionFactory;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Connection c = ConnectionFactory.getConnection();
        //ConnectionFactory.closeConnection(c);
        // projectController.save(project);
         ProjectController projectController = new ProjectController();
         Project project = new Project();
        //  project.setId(1);
        //  project.setName("Novo nome do projeto");
        //  project.setDescription("description");
        //   projectController.update(project);
        //  List<Project> projects = projectController.getAll();
        //  System.out.println("Total de projetos " + projects.size());
         projectController.removeById(3);
        //ProjectController projectController = new ProjectController();
        // Project project = new Project();
        //  project.setName("React");
        // project.setDescription("tarefa de hoje");
        //  projectController.save(project);
        //  TaskController primeiraTarefa = new TaskController();
        //  task tarefa = new task();
        //  tarefa.setId(1);
        //  tarefa.setIdProject(2);
        //   tarefa.setName("Definir app");
        //   tarefa.setDescription("Receber word dos amigos");
        //   tarefa.setCompleted(false);
        //   tarefa.setDeadline(new Date());
        //    primeiraTarefa.save(tarefa);
        TaskController primeiraTarefa = new TaskController();

        task tarefa = new task();

        // tarefa.setIdProject(2);
        // tarefa.setName("Novo nome");
        // tarefa.setCompleted(true);
        //   tarefa.setDeadline(new Date());
        //   tarefa.setId(1);
        //  primeiraTarefa.update(tarefa);
        List<task> tasks = primeiraTarefa.getAll(2);
        System.out.println("Total de tarefas é: " + tasks.size());
        
      //  primeiraTarefa.removeById(1);
      
      

    }
}
