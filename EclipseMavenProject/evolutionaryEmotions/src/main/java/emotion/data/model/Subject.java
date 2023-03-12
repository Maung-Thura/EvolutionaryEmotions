package emotion.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import emotion.util.Iso8601DateSerializer;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A person or thing that causes an event
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Subject")
@Table(name = "subject")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Subject {
	@Id
	@GeneratedValue
	private Integer subjectId;

	private String name;

	private String identifiedAs;

	@JsonSerialize(using = Iso8601DateSerializer.class)
	private long knownOn;
}
