package ru.itis.dmitrieva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dmitrieva.models.Tasks;

//в интерфейсе не объявлены дополнительные методы. Однако, за счет наследования от JpaRepository,
// TasksRepository наследует ряд стандартных методов для работы с сущностью Tasks,
// таких как save, findById, findAll, delete, и т. д.
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
