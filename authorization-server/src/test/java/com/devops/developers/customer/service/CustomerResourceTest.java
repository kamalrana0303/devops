package com.devops.developers.customer.service;

import com.devops.developers.config.WithCustomerUser;
import com.devops.developers.customer.dao.UserDao;
import com.devops.developers.customer.resource.CustomerResource;
import com.devops.developers.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
class CustomerResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomerDetailsServiceImpl customerService;

//    @Test
//    @DisplayName("When Calling /customer/{username} endpoint without authentication we expect 401 status code")
//    void testUnauthenticatedgetCustomerEndPoint() throws Exception {
//        mockMvc.perform(get("/customer/kamal")
//                        .with(jwt()))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser(username="kamal")     // creating a user in mock security context
//    @DisplayName("when calling /customer/{username} endpoint with authentication")
//    void testAuthenticatedGetCustomerEndPoint() throws Exception{
//        mockMvc.perform(get("/customer/kamal"))
//                .andExpect(status().isForbidden());
//    }

    @Test
    @WithCustomerUser(authority ="ADMIN")
    @DisplayName("when calling /customer/{username} endpoint with authentication do not having proper authority")
    void testAuthenticatedWithoutProperAuthorityEndpoint() throws Exception{
        CustomerDto customerDto= new CustomerDto();
        customerDto.setUsername("kamal");
        given(customerService.getCustomerByName(anyString())).willReturn(customerDto);
        mockMvc.perform(get("/customer/kamal"))
                .andExpect(status().isOk());
    }

//    @Autowired
//    CustomerService customerService;
//    @Test
//    void testUnauthenticatedGetCustomerByName(){
//        assertThrows(AuthenticationCredentialsNotFoundException.class, ()->{ customerService.getCustomerByName("kamal");});
//    }
//    @Test
//    @WithMockUser(username="kamal",authorities = "write")
//    void testAuthenticatedWithoutProperAuthentication(){
//      customerService.getCustomerByName("kamal");
//    }


}