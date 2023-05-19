package ru.itis.dmitrieva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dmitrieva.models.File;

import java.util.Optional;
//благодаря использованию Spring Data JPA и наименованным методам, разработчику не нужно писать реализацию
// этого метода самостоятельно. Spring Data JPA автоматически выполнит запрос и вернет соответствующий файл,
// если он будет найден.
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByStorageFileName(String storageFileName); //поиск файла по имени хранения
}

