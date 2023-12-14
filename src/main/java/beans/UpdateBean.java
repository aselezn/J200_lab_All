package beans;

import entity.AddressEntity;
import entity.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import repository.DBManager;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UpdateBean {

    @EJB
    private DBManager dbManager;

    private static final String IP_REGEX = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
    private static final String MAC_REGEX = "^([0-9A-Fa-f]{1,2}[:-]){5}([0-9A-Fa-f]{1,2})$";

    public void validateClient(String clientName, String clientType) throws ValidationUtils.ValidationException {
        ValidationUtils.validateNotEmpty(clientName);
        ValidationUtils.validateLength(clientName, 100, "Имя клиента");
        ValidationUtils.validateWithRegex(clientName, "[а-яА-ЯёЁ\\- ,.]+", "Имя клиента может содержать только русские буквы и символы [- , .]");
        ValidationUtils.validateNotEmpty(clientType);
        if (!clientType.equals("Юридическое лицо") && !clientType.equals("Физическое лицо")) {
            throw new ValidationUtils.ValidationException("Тип клиента имеет недопустимое значение");
        }
    }

    public void validateAddress(String ipAddress,
                                String macAddress,
                                String model,
                                String address) throws ValidationUtils.ValidationException {
        ValidationUtils.validateNotEmpty(ipAddress);
        ValidationUtils.validateWithRegex(ipAddress, IP_REGEX, "Недопустимый формат IP-адреса");
        ValidationUtils.validateNotEmpty(macAddress);
        ValidationUtils.validateWithRegex(macAddress, MAC_REGEX, "Недопустимый формат MAC-адреса");
        ValidationUtils.validateNotEmpty(model);
        ValidationUtils.validateLength(model, 100, "Модель устройства");
        ValidationUtils.validateNotEmpty(address);
        ValidationUtils.validateLength(address, 200, "Адрес");
    }

    public ClientEntity findByClientId(Integer id) {
        return dbManager.findByClientId(id);
    }
    public AddressEntity findByAddressId(Integer id) {
        return dbManager.findByAddressId(id);
    }

    public void deleteClient(String id) {
        dbManager.removeClient(Integer.parseInt(id));
    }

    public void deleteAddress(String id) {
        dbManager.removeAddress(Integer.parseInt(id));
    }

    public void updateClient(String clientId,
                             String clientName,
                             String clientType,
                             String addressId,
                             String ip,
                             String mac,
                             String model,
                             String address) {

        ClientEntity client = dbManager.findByClientId(Integer.parseInt(clientId));
        if (client != null) {
            client.setClientName(clientName);
            client.setType(clientType);
            dbManager.update(client);
        }

        AddressEntity addressEntity = dbManager.findByAddressId(Integer.parseInt(addressId));
        if (addressEntity != null) {
            addressEntity.setIp(ip);
            addressEntity.setMac(mac);
            addressEntity.setModel(model);
            addressEntity.setAddress(address);
            dbManager.update(addressEntity);
        }

    }


    public void addNewAddress(
            String clientId,
            String ip,
            String mac,
            String model,
            String address
    ) {

        ClientEntity client = dbManager.findByClientId(Integer.parseInt(clientId));
        if (client != null) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setIp(ip);
            addressEntity.setMac(mac);
            addressEntity.setModel(model);
            addressEntity.setAddress(address);
            addressEntity.setClient(client);
            dbManager.create(addressEntity);
        }

    }

    public void createNewClient(
            String clientName,
            String clientType,
            String ip,
            String mac,
            String model,
            String address
    ) {

        ClientEntity client = new ClientEntity();
        client.setClientName(clientName);
        client.setType(clientType);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setIp(ip);
        addressEntity.setMac(mac);
        addressEntity.setModel(model);
        addressEntity.setAddress(address);
        addressEntity.setClient(client);

        List<AddressEntity> addresses = new ArrayList<>();
        addresses.add(addressEntity);
        client.setAddresses(addresses);

        dbManager.create(client);

    }
}
