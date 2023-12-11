package dto;

import entity.AddressEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"ip", "mac", "model", "address"})
public class AddressDto {

    private Integer id;
    private String ip;
    private String mac;
    private String model;
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
