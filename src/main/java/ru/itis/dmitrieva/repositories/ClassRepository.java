package ru.itis.dmitrieva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.dmitrieva.models.Class;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query(nativeQuery = true, value = "select c.* from classes c join accounts_classes ac on c.id = ac.class_id " +
                    "join accounts a on ac.account_id = a.id where a.id = :id")
    List<Class> findClassesByAccountsId(@Param("id") Long id);
}
//аннотация @Query позволяет определить пользовательский SQL-запрос, который будет выполнен для получения результатов