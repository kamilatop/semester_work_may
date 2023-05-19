package ru.itis.dmitrieva.services;

import ru.itis.dmitrieva.dto.PostDto;
import java.util.List;

public interface PostService {
    void addPost(PostDto postDto, Long accountId);
    List<PostDto> getAllPosts();
    void deletePost(Long postId);
    List<PostDto> searchPostByTitle(String title);
}
