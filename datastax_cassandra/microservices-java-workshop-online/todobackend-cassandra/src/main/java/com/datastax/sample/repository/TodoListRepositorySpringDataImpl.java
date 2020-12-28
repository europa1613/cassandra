package com.datastax.sample.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.datastax.sample.model.Task;
import com.datastax.sample.springdata.TaskSpringData;
import com.datastax.sample.springdata.TaskSpringDataRepository;

/**
 * Default in-memory implementation of the repository.
 *
 * Help to have a working application even in the beginning.
 *
 * @author Cedrick LUNVEN (@clunven)
 */

@Repository("todobackend.repo.spring-data-cassandra")
public class TodoListRepositorySpringDataImpl implements TodoListRepository {

  private final TaskSpringDataRepository taskDao;

  public TodoListRepositorySpringDataImpl(TaskSpringDataRepository taskDao) {
    this.taskDao = taskDao;
  }

  @Override
  public List<Task> findAll() {
    return taskDao.findAll().stream()
        .map(TaskSpringData::mapAsTask)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteAll() {
    taskDao.deleteAll();
  }

  @Override
  public Optional<Task> findById(UUID uid) {
    if (null == uid) {
      return Optional.empty();
    }
    Optional<TaskSpringData> entity = taskDao.findById(uid);
    Optional<TaskSpringData> byTaskByIdO = taskDao.findByTaskByIdO(uid);
    System.out.println("==========================>>>>>>" + byTaskByIdO);
    return entity.map(TaskSpringData::mapAsTask);
  }

  @Override
  public void upsert(Task dto) {
    if (null != dto) {
      taskDao.save(new TaskSpringData(dto));
    }
  }

  @Override
  public void delete(UUID uid) {
    TaskSpringData tsd = new TaskSpringData();
    tsd.setUuid(uid);
    taskDao.delete(tsd);
  }
}
