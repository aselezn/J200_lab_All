package dto;

import entity.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"clientName", "type", "added", "addresses"})
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "client") // корневой элемент для XML
@XmlAccessorType(XmlAccessType.FIELD) // JAXB должен использовать поля для связывания
@Builder
public class ClientDto {

    @XmlAttribute(name = "client_id")
    private Integer id;

    @XmlAttribute(name = "client_name")
    private String clientName;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "added")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate added;

    @XmlElement(name = "address")
    @XmlElementWrapper(name = "addresses") // обертка для коллекции из элементов address
    private List<AddressDto> addresses = new ArrayList<>();

    public static ClientDto getClientDtoByEntity(ClientEntity entity){
        ClientDto dto = new ClientDto();
        dto.id = entity.getId();
        dto.clientName = entity.getClientName();
        dto.type = entity.getType();
        dto.added = entity.getAdded();
        dto.addresses = entity.getAddresses()
                .stream()
                .map(AddressDto::getAddressByEntity)
                .collect(Collectors.toList());
        return dto;
    }
}
