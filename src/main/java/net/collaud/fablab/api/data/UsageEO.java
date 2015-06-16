package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_usage")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class UsageEO extends AbstractDataEO<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id", nullable = false)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Column(name = "price_hour", nullable = false)
    private double pricePerHour;

    @Column(name = "minutes")
    private int minutes;

    @Column(name = "additional_cost", nullable = false)
    private double additionalCost;

    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEO user;

    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MachineEO machine;

    @JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MembershipTypeEO membershipType;

    @Transient
    @JsonProperty
    private boolean directPaid;

    public double getTotalPrice() {
        return pricePerHour * minutes / 60 + additionalCost;
    }

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public UsageEO() {
        this(null);
    }

    public UsageEO(Integer id) {
        this.active = true;
        this.id = id;
    }

    public UsageEO(Date dateStart, double pricePerHour, int minutes, double additionalCost, String comment, UserEO user, MachineEO machine, MembershipTypeEO membershipType) {
        this(null, dateStart, pricePerHour, minutes, additionalCost, comment, user, machine, membershipType, false);
    }

    public UsageEO(Integer id, Date dateStart, double pricePerHour, int minutes, double additionalCost, String comment, UserEO user, MachineEO machine, MembershipTypeEO membershipType, boolean directPaid) {
        this.id = id;
        this.dateStart = dateStart;
        this.pricePerHour = pricePerHour;
        this.minutes = minutes;
        this.additionalCost = additionalCost;
        this.comment = comment;
        this.user = user;
        this.machine = machine;
        this.membershipType = membershipType;
        this.directPaid = directPaid;
        this.active = true;
    }

}
