package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@NamedQueries({
        @NamedQuery(name="clients.findAll", query = "SELECT c FROM ClientEntity c"),
        @NamedQuery(name="clients.findById", query = "SELECT c FROM ClientEntity c WHERE c.id = :id")
})
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid", nullable = false)
    private Integer id;

    @NonNull
    @Basic(optional = false)
    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;

    @NonNull
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @NonNull
    @Basic(optional = false)
    @Column(name = "added", nullable = false)
    private LocalDate added;

    @OneToMany(mappedBy = "clientid")
    private List<AddressEntity> addresses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}