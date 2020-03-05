package example.repo;

import example.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepo extends JpaRepository<PostImage, Long> {
}
