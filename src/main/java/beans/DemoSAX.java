package beans;

import dto.AddressDto;
import dto.ClientAddressDto;
import dto.ClientDto;
import jakarta.ejb.Stateless;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DemoSAX extends DefaultHandler {

    @Getter
    private List<ClientAddressDto> clients = new ArrayList<ClientAddressDto>();
    private ClientDto client;
    private AddressDto currentAddress;
    private String clientNameFilter;

    public void setFilters(String clientNameFilter) {
        this.clientNameFilter = clientNameFilter != null ? clientNameFilter.toLowerCase() : null;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Начало обработки документа");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Конец обработки документа");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "client" -> {
                String clientNameAttr = attributes.getValue("client_name");
                if (clientNameAttr != null) {
                    String clientName = clientNameAttr.toLowerCase();
                    if (clientNameFilter == null || clientName.contains(clientNameFilter.toLowerCase())) {
                        client = ClientDto.builder()
                                .id(Integer.parseInt(attributes.getValue("client_id")))
                                .clientName(attributes.getValue("client_name"))
                                .type(attributes.getValue("type"))
                                .added(LocalDate.parse(attributes.getValue("added")))
                                .build();
                    }

                }
            }
            case "address" -> {
                if (client != null) {
                    currentAddress = AddressDto.builder()
                            .id(Integer.parseInt(attributes.getValue("address_id")))
                            .ip(attributes.getValue("ip"))
                            .mac(attributes.getValue("mac"))
                            .model(attributes.getValue("model"))
                            .address(attributes.getValue("client_address"))
                            .build();
                }
            }
        }
    }

    public void resetClients() {
        clients.clear();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("client")) {
            if (client != null) {
                ClientAddressDto clientAddressDto = new ClientAddressDto(client, currentAddress);
                clients.add(clientAddressDto);
                client = null;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

}
