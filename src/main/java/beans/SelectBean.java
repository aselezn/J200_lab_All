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

// можно сделать 1 стрим
    public List<ClientAddressDto> filterClients(String clientName, String address, String type) {
        System.out.println("Before filter - clientName: " + clientName + ", address: " + address + ", type: " + type);

        List<ClientAddressDto> allClients = findAll();

            allClients = allClients.stream()
                    .filter(client -> {
                        if (clientName != null && !clientName.trim().isEmpty())
                        return client.getClientName().toLowerCase().contains(clientName.toLowerCase());
                        else return true;
                    })
                    .filter(client -> {
                        if (address != null && !address.trim().isEmpty())
                        return client.getClientAddress().toLowerCase().contains(address.toLowerCase());
                        else return true;
                    })
                    .filter(client -> {
                        if (type != null && !type.isEmpty())
                            return client.getType().equals(type);
                        else return true;
                    })
                    .collect(Collectors.toList());

        System.out.println("After filter - filtered clients: " + allClients);

        return allClients;
    }
}
