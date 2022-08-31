package com.testblancco.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class OrderTest {

    @Test
    public void deliverOrderWorks()
    {

        CustomerStub customerStub =   Mockito.mock(CustomerStub.class);
        KitchenStub kitchenStub =   Mockito.mock(KitchenStub.class);
        DishStub dishStub =   Mockito.mock(DishStub.class);

        when(customerStub.getCustomer()).thenReturn( new Customer("tushar"));
        when(kitchenStub.getKitchen()).thenReturn( new Kitchen("lumbini"));
        when(dishStub.getDish()).thenReturn( new Dish("parantha"));

        Order order = new Order(kitchenStub.getKitchen(),customerStub.getCustomer(),dishStub.getDish());
        assertTrue
        (order.deliverOrder());

    }

}
