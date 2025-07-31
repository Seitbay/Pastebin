package ru.bull.pastebin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bull.pastebin.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByHash(String hash);

}
