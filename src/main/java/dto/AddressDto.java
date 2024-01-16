package dto;

import entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"ip", "mac", "model", "address"})
@RequiredArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD) // JAXB должен использовать поля для связывания
@Builder
public class AddressDto {

    @XmlAttribute(name = "address_id")
    private Integer id;

    @XmlAttribute(name = "ip")
    private String ip;

    @XmlAttribute(name = "mac")
    private String mac;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "client_address")
    private String address;

    public static AddressDto getAddressByEntity(AddressEntity entity){
        AddressDto addressDto = new AddressDto();
        addressDto.id = entity.getId();
        addressDto.ip = entity.getIp();
        addressDto.mac = entity.getMac();
        addressDto.model = entity.getModel();
        addressDto.address = entity.getAddress();
        return addressDto;
    }
}
