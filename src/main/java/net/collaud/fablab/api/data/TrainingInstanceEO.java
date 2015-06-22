package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>TrainingInstance</tt>
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_training_instance")
@Getter
@Setter
@ToString
@Where(clause="active=1")
public class TrainingInstanceEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_instance_id", nullable = false)
    private Integer id;

    @Column(name = "training_date", nullable = false )
    @Temporal(TemporalType.DATE)
    private Date trainingDate;

    @Column(name = "training_price", nullable = true )
    private Float trainingPrice;

    @Column(name = "note", nullable = true )
    @Type(type = "text")
    private String note;

    @JoinColumn(name = "training_id", referencedColumnName = "training_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrainingEO training;

    @Column(name="active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public TrainingInstanceEO() {
        this(null);
    }

    public TrainingInstanceEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
