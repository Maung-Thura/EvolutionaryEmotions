package emotion.neural.network;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import emotion.data.model.Event;
import emotion.type.EmotionType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a neuron that traverses within the neural network boundary
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Emotion")
@Table(name = "emotion")
@TypeDef(name = "json", typeClass = JsonType.class)
class Emotion {
	@Id
	@GeneratedValue
	private Integer emotionId;

	@Column(nullable = true)
	private EmotionType emotionType;

	private int processedMagnitude;

	private int threshold;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "eventId")
	private Event event;

}
