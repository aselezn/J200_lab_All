package dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientListWrapper {

    @XmlElement(name = "client")
    private List<ClientDto> clients;

    public ClientListWrapper() {
        this.clients = new ArrayList<>();
    }

    public ClientListWrapper(List<ClientDto> clients) {
        this.clients = clients;
    }

}
