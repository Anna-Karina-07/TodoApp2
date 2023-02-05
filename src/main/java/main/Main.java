/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.ProjectController;
import controller.TaskController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

/**
 *
 * @author Lï¿½ssia Oliveira
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here

        //
        // ProjectController projectController = new ProjectController();
        //
        // Project project = new Project();
        // project.setName("Projeto teste");
        // project.setDescription("description");
        // projectController.save(project);
        //
        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setId(1);
        project.setName("Novo nome de projeto");
        project.setDescription("description");
        projectController.save(project);
        //project.getUpdateAt();
        //project.setUpdateAt();
        //projectController.update(project);

        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos = " + projects.size());

        projectController.removeById(0);

        TaskController taskController = new TaskController();

        Task task = new Task();
        task.setIdProject(1);
        task.setName("Criar as telas da aplicação");
        task.setDescription("Devem ser criadas telas para os cadastros");
        task.setNotes("Sem notas");
        task.setIsCompleted(false);
        task.setDeadline(new Date());

        taskController.save(task);

        task.setName("Alterar telas da aplicação");
        taskController.update(task);
        List<Task> tasks = taskController.getAll(1);
        System.out.println("Total de tarefas = " + tasks.size());

    }

}
