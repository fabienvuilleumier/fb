package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_group")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class GroupEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer id;

    @JsonIgnore
    @Column(name = "technicalname", nullable = false)
    private String technicalname;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<UserEO> users;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<RoleEO> roles;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public GroupEO() {
        this(null);
    }

    public GroupEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
