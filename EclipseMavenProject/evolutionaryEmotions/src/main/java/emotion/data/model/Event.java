package emotion.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import emotion.type.EventType;
import emotion.util.Iso8601DateSerializer;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * an event evokes one or more emotions. Ref: The James-Lange Theory of Emotion
 * 
 * @see https://en.wikipedia.org/wiki/James%E2%80%93Lange_theory
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Event")
@Table(name = "event")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Event {
	@Id
	@GeneratedValue
	private Integer eventId;

	private EventType eventType;

	private String description;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "subjectId")
	private Subject causedBy;

	@JsonSerialize(using = Iso8601DateSerializer.class)
	private long causedOn;
}
