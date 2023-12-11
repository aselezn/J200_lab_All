package dto;

import entity.AddressEntity;
import entity.ClientEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"clientName", "type", "added", "addresses"})
public class ClientDto {

    private Integer id;
    private String clientName;
    private String type;
    private LocalDate added;
    private List<AddressDto> addresses = new ArrayList<>();

    public static ClientDto getClientDtoByEntity(ClientEntity entity){
        ClientDto dto = new ClientDto();
        dto.id = entity.getId();
        dto.clientName = entity.getClientName();
        dto.type = entity.getType() ;
        dto.addresses = entity.getAddresses()
                .stream()
                .map(AddressDto::getAddressByEntity)
                .collect(Collectors.toList());
        return dto;
    }
}
