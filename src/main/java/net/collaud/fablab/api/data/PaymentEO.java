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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_payment")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class PaymentEO extends AbstractDataEO<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "date_payement", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePayment;

    @Column(name = "comment")
    private String comment;

    @JsonProperty("paymentUser")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEO user;

    @JsonProperty("payementCashier")
    @JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEO cashier;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public PaymentEO() {
        this(null);
    }

    public PaymentEO(Integer id) {
        this.active = true;
        this.id = id;
    }

    public PaymentEO(Date datePayement, double total, UserEO user, UserEO cashier, String comment) {
        this.active = true;
        this.id = 0;
        this.datePayment = datePayement;
        this.total = total;
        this.user = user;
        this.cashier = cashier;
        this.comment = comment;
    }
}
