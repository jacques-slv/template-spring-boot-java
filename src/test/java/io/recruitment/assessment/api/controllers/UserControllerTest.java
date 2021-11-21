package io.recruitment.assessment.api.controllers;//package io.recruitment.assessment.api.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import service.ISqlServerService;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//class ControllerTest {
//    private MockMvc mockMvc;
//
//    @Mock
//    private ISqlServerService sqlServerService;
//
//    @InjectMocks
//    private UserController userController;
//
//    private JacksonTester<User> userJacksonTesterUser;
//
//    @BeforeEach
//    public void setup() {
//        JacksonTester.initFields(this, new ObjectMapper());
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//
//    @Test
//    void contextLoads() throws Exception{
//        assertThat(userController).isNotNull();
//    }
//
//    @Test
//    void canAddUserByUsernameIfNotExists() throws Exception{
//
//        // set expectation
//        MockHttpServletResponse response = mockMvc.perform(
//                post("/adduser/testuser/test/user/admin/").contentType(MediaType.APPLICATION_JSON).content(
//                        userJacksonTesterUser.write(new User( "testuser", "test", "user", "admin")).getJson()
//                )).andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(
//                "{\"message\":\"user testuser has been added\"}");
//
//    }
//
//    @Test
//    void canRemoveUserByUsernameWhenExists() throws Exception{
//
//
//    }
//
//    @Test
//    void canGetUserByUsernameWhenExists() throws Exception{
//
//
//    }
//
//
//}

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.TestUtils;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    JacksonTester<User> userJacksonTesterUser;
    UserController userController;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void testCreateUser() throws Exception {
//        JacksonTester.initFields(this, new ObjectMapper());
//        MockHttpServletResponse response = mockMvc.perform(
//                post("/adduser/testuser/test/user/admin/").contentType(MediaType.APPLICATION_JSON).content(
//                        userJacksonTesterUser.write(new User("testuser", "test", "user","admin")).getJson()
//                )).andReturn().getResponse();

    }
}

