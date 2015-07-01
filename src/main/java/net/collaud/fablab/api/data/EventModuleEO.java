package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>EventModule</tt>
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event_module")
@Getter
@Setter
@ToString
@Where(clause="active=1")
public class EventModuleEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_module_id", nullable = true)
    private Integer id;

    @Column(name = "name", nullable = true )
    private String name;

    @Column(name = "description", nullable = false )
    @Type(type = "text")
    private String description;

    @Column(name="active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventModuleEO() {
        this(null);
    }

    public EventModuleEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
