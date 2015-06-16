package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_role")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class RoleEO extends AbstractDataEO<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "technicalname", nullable = false)
    private String technicalname;

    @JsonIgnore
    @JoinTable(name = "r_group_role",
            joinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, updatable = false)})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<GroupEO> groups;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public RoleEO() {
        this(null, null);
    }

    public RoleEO(Integer id) {
        this(id, null);
    }

    public RoleEO(Integer roleId, String name) {
        this.active = true;
        this.id = roleId;
        this.name = name;
    }

}
