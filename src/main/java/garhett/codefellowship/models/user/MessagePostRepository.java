package garhett.codefellowship.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagePostRepository extends JpaRepository<MessagePost, Long> {

    public MessagePost findByUsername(String username);

    public List<MessagePost> findAll();
}
