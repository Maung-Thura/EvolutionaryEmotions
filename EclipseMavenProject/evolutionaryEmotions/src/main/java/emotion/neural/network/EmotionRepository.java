package emotion.neural.network;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import emotion.data.model.Subject;

/**
 * As {@link Emotion} exists within the neural network, this repository cannot
 * be under emotion.repository package
 *
 */

interface EmotionRepository extends JpaRepository<Emotion, Integer>, JpaSpecificationExecutor<Emotion> {

	static final String EVENT = "event";
	static final String CAUSED_BY = "causedBy";
	static final String SUBJECT_ID = "subjectId";

	static Specification<Emotion> retrievePastEmotions(Subject subject) {
		return (emotion, cq, cb) -> cb.equal(emotion.get(EVENT).get(CAUSED_BY).get(SUBJECT_ID), subject.getSubjectId());
	}
}
