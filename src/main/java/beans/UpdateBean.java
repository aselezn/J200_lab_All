package beans;

import entity.AddressEntity;
import entity.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.DBManager;
import validators.AddressValidator;
import validators.ClientValidator;

import java.io.IOException;
import java.util.List;

@Stateless
public class UpdateBean {

    @EJB
    private DBManager dbManager;

    public void deleteClient(Integer id) {
        dbManager.removeClient(id);
    }

    public void deleteAddress(Integer id) {
        dbManager.removeAddress(id);
    }

    public void createNewClient(
            HttpServletRequest request,
            HttpServletResponse response,
            String clientName,
            String clientType,
            String ip,
            String mac,
            String address
    ) throws ServletException, IOException {
        boolean validateClientResult = ClientValidator.validate(request, response, clientName, clientType);
        boolean validateAddressResult = AddressValidator.validate(request, response, ip, mac, address);


        if (validateClientResult && validateAddressResult) {

            ClientEntity client = new ClientEntity();
            client.setClientName(clientName);
            client.setType(clientType);

            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setIp(ip);
            addressEntity.setMac(mac);
            addressEntity.setClient(client);

            client.setAddresses(List.of(addressEntity));

            dbManager.create(client);
            response.sendRedirect("view-list");
        }

    }
}
