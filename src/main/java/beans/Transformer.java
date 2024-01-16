package beans;

import dto.ClientDto;
import dto.ClientListWrapper;
import entity.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.SneakyThrows;
import repository.DBManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class Transformer {

    @EJB
    private DBManager dbManager;

    @SneakyThrows
    public Path convertToXML() {
        List<ClientEntity> clientEntities = dbManager.findAll();
        List<ClientDto> clients = clientEntities.stream()
                .map(ClientDto::getClientDtoByEntity)
                .collect(Collectors.toList());

        Path path = Files.createTempDirectory("lab210");
        path.toFile().deleteOnExit();

        try {
            JAXBContext context = JAXBContext.newInstance(ClientListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            ClientListWrapper wrapper = new ClientListWrapper(clients);
            marshaller.marshal(wrapper, new File(path + "/Clients.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Файл сохранен в: " + path.toAbsolutePath().toString());

        return path;
    }
}
