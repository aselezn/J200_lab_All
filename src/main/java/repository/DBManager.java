package repository;

import entity.AddressEntity;
import entity.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class DBManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ClientEntity> findAll() {
        return entityManager.createNamedQuery("clients.findAll").getResultList();
    }

    public ClientEntity findByClientId(Integer id){

        return entityManager.find(ClientEntity.class, id);
    }

    public AddressEntity findByAddressId(Integer id){

        return entityManager.find(AddressEntity.class, id);
    }

    public void removeClient(Integer id){
        ClientEntity client = findByClientId(id);
        entityManager.remove(client);
    }

    public void removeAddress(Integer id){
        AddressEntity address = findByAddressId(id);
        entityManager.remove(address);
    }

    public void create(ClientEntity client){

        entityManager.merge(client);
    }

    public ClientEntity update(ClientEntity client){

        return entityManager.merge(client);
    }
}
