package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>EventPerson</tt>
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event_person")
@Getter
@Setter
@ToString
@Where(clause="active=1")
public class EventPersonEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_person_id", nullable = true)
    private Integer id;

    @Column(name = "lastname", nullable = true )
    private String lastname;

    @Column(name = "firstname", nullable = true )
    private String firstname;

    @Column(name = "email", nullable = true )
    private String email;

    @Column(name="active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventPersonEO() {
        this(null);
    }

    public EventPersonEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
