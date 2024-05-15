package com.tasks.taskproject.service;

import com.tasks.taskproject.dto.TaskDTO;
import com.tasks.taskproject.dto.TaskPersonDTO;

import java.util.List;

public interface ITaskService {

    public List<TaskPersonDTO> getTasksWithPersons();

    public TaskPersonDTO findTaskWithPerson(Long id);

    public void saveTask(TaskDTO taskDTO);

    public void editTask(Long id, TaskDTO taskDTO);

    public void deleteTask(Long id);
}
