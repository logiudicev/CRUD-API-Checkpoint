package milsoftwarefsactory.CRUD;
import milsoftwarefsactory.CRUD.dao.UserRepository;
import milsoftwarefsactory.CRUD.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndPoint1Test {

    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("{\"email\": \"steve.jobs@apple.com\",\"password\": \"supbro\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("{\"email\": \"steve.jobs@apple.com\",\"password\": \"supbro\"}", instanceOf(UserEntity.class) ));
    }
    @Test
    @Transactional
    @Rollback
    public void testPatch() throws Exception {
        MockHttpServletRequestBuilder request = patch("/users/2")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("{\"email\": \"steve.jobs@apple.com\",\"password\": \"supbro\"}");
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws Exception {
        MockHttpServletRequestBuilder request = delete("/users/2")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("{\"email\": \"steve.jobs@apple.com\",\"password\": \"supbro\"}");
    }

}
