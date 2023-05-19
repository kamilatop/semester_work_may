package ru.itis.dmitrieva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dmitrieva.models.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByState(Post.State state);
    List<Post> findAllByTitleLike(String title); // метод автоматически генерирует SQL-запрос для поиска всех записей Post,
    // у которых заголовок содержит указанную подстроку
}
