package emotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emotion.data.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}