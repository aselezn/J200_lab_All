package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
@NamedQueries({
        @NamedQuery(name="addresses.findAll", query = "SELECT t FROM AddressEntity t"),
        @NamedQuery(name="addresses.findById", query = "SELECT c FROM AddressEntity c WHERE c.id = :id")
})
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid", nullable = false)
    private Integer id;

    @NonNull
    @Basic(optional = false)
    @Column(name = "ip", nullable = false, length = 25)
    private String ip;

    @NonNull
    @Basic(optional = false)
    @Column(name = "mac", nullable = false, length = 20)
    private String mac;

    @NonNull
    @Basic(optional = false)
    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @NonNull
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clientid", nullable = false)
    private ClientEntity clientid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}