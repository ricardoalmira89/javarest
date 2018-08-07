package hello.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import hello.Entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);
}