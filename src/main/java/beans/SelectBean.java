package beans;

import dto.ClientAddressDto;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import repository.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SelectBean {

    @EJB
    private DBManager dbManager;


    public List<ClientAddressDto> findAll(){
        List<ClientAddressDto> list = new ArrayList<>();
        dbManager.findAll().forEach(client -> {
            if (client.getAddresses()!=null && !client.getAddresses().isEmpty()){
                client.getAddresses().forEach(clientPas -> list.add(new ClientAddressDto(client, clientPas)));
            } else {
                list.add(new ClientAddressDto(client, null));
            }
        });
        System.out.println(list);
        return list;
    }

    public List<ClientAddressDto> filterClients(String clientName, String address, String type) {
        System.out.println("Before filter - clientName: " + clientName + ", address: " + address + ", type: " + type);

        List<ClientAddressDto> allClients = findAll();

        if (clientName != null && !clientName.trim().isEmpty()) {
            allClients = allClients.stream()
                    .filter(client -> client.getClientName().toLowerCase().contains(clientName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (address != null && !address.trim().isEmpty()) {
            allClients = allClients.stream()
                    .filter(client -> client.getClientAddress().toLowerCase().contains(address.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (type != null && !type.isEmpty()) {
            allClients = allClients.stream()
                    .filter(client -> client.getType().equals(type))
                    .collect(Collectors.toList());
        }
        System.out.println("After filter - filtered clients: " + allClients);

        return allClients;
    }
}
