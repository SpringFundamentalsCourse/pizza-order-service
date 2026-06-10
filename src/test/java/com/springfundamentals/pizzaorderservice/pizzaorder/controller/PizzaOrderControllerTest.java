package com.springfundamentals.pizzaorderservice.pizzaorder.controller;

import com.springfundamentals.pizzaorderservice.pizzaorder.service.PizzaOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaOrderController.class)
public class PizzaOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PizzaOrderService pizzaOrderService;

    private JacksonTester<List<PizzaOrderDto>> jacksonTester;

    @BeforeEach
    public void setUp() {
        JsonMapper objectMapper = new JsonMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void twoPizzaOrders_getAllOrders_twoOrders() throws Exception {
        doReturn(asList(new PizzaOrderDto("1", emptyList()), new PizzaOrderDto("2", emptyList()))).when(pizzaOrderService).findAll();

        mockMvc.perform(get("/pizza-orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                          "orderId": "1" ,
                          "orderItems": []
                        },{
                          "orderId": "2" ,
                          "orderItems": []
                        }]"""));

    }

    @Test
    public void twoPizzaOrders_getAllOrders_twoOrdersJacksonTester() throws Exception {
        doReturn(asList(new PizzaOrderDto("1", emptyList()), new PizzaOrderDto("2", emptyList()))).when(pizzaOrderService).findAll();

        mockMvc.perform(get("/pizza-orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(jacksonTester.write(asList(new PizzaOrderDto("1", emptyList()), new PizzaOrderDto("2", emptyList()))).getJson()));
    }
}
