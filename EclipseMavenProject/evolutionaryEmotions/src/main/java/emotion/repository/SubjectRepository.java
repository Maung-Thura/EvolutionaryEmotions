package emotion.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import emotion.data.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>, JpaSpecificationExecutor<Subject> {

	static final String NAME = "name";
	static final String IDENTIFIED_AS = "identifiedAs";

	static Specification<Subject> isKnownSubject(String name, String identifiedAs) {
		return (subject, cq, cb) -> cb.and(cb.equal(subject.get(NAME), name),
				cb.equal(subject.get(IDENTIFIED_AS), identifiedAs));
	}
}
