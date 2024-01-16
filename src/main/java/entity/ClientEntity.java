package entity;


import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@NamedQueries({
        @NamedQuery(name="clients.findAll", query = "SELECT c FROM ClientEntity c"),
        @NamedQuery(name="clients.findById", query = "SELECT c FROM ClientEntity c WHERE c.id = :id")
})
@Builder
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

    @Basic(optional = false)
    @Column(name = "added", nullable = false, insertable = false)
    private LocalDate added;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
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