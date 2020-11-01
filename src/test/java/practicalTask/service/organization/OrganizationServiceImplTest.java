package practicalTask.service.organization;

import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import practicalTask.dao.user.UserDao;
import practicalTask.dao.user.UserDaoImpl;
import practicalTask.model.Organization;
import practicalTask.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
public class OrganizationServiceImplTest {


    public OrganizationServiceImplTest() {
    }


    @Test
    public void getOrganization() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/organization/2", String.class);
        System.out.println(response.getBody());
    }

    @Test
    public void getOrganizationList() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }
}