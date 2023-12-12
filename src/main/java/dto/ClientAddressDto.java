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
    private Integer clientId;
    private String clientName;
    private String type;
    private LocalDate added;
    private Integer addressId;
    private String ip;
    private String mac;
    private String model;
    private String clientAddress;

    public ClientAddressDto(ClientEntity client, AddressEntity address){

        clientId = client.getId();
        clientName = client.getClientName();
        type = client.getType();
        added = client.getAdded();
        if(address!=null){
            addressId = address.getId();
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
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
}
