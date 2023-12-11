package dto;

import entity.AddressEntity;
import entity.ClientEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class ClientAddressDto {
    private Integer id;
    private String clientName;
    private String type;
    private LocalDate added;
    private String ip;
    private String mac;
    private String model;
    private String clientAddress;

    public ClientAddressDto(ClientEntity client, AddressEntity address){

        id = client.getId();
        clientName = client.getClientName();
        type = client.getType();
        added = client.getAdded();
        if(address!=null){
            ip = address.getIp();
            mac = address.getMac();
            model = address.getModel();
            clientAddress = address.getAddress();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAddressDto that = (ClientAddressDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
