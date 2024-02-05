// ********RoostGPT********
/*
Test generated by RoostGPT for test demoTestGitlab using AI Type Open AI and AI Model gpt-4

1. Scenario: Check if the `checkoutOrder` function properly calculates the final price after applying the discount. 
   - Input: Provide an `OrderRequest` object with a certain price and discount.
   - Expected Output: The `OrderResponse` object should return the price after discount.

2. Scenario: Check if the `checkoutOrder` function sends the notification email correctly.
   - Input: Provide an `OrderRequest` object with a valid email id.
   - Expected Output: The `OrderResponse` object should contain a message indicating that the email was sent successfully.

3. Scenario: Check if the `checkoutOrder` function handles an invalid email id.
   - Input: Provide an `OrderRequest` object with an invalid email id.
   - Expected Output: The `OrderResponse` object should contain a message indicating that the email could not be sent.

4. Scenario: Check if the `checkoutOrder` function correctly handles negative prices.
   - Input: Provide an `OrderRequest` object with a negative price.
   - Expected Output: The `OrderResponse` object should contain an error message or a specific response indicating that negative prices are not valid.

5. Scenario: Check if the `checkoutOrder` function correctly handles a null `OrderRequest`.
   - Input: Provide a null `OrderRequest`.
   - Expected Output: The function should either handle the null input gracefully and return an appropriate response, or throw a specific exception.

6. Scenario: Check if the `checkoutOrder` function correctly handles an `OrderRequest` with null or empty fields.
   - Input: Provide an `OrderRequest` object with null or empty fields.
   - Expected Output: The function should either handle the null/empty fields gracefully and return an appropriate response, or throw a specific exception.
*/

// ********RoostGPT********
package com.javatechie.pm.api.service;

import com.javatechie.pm.api.dto.OrderRequest;
import com.javatechie.pm.api.dto.OrderResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class OrderService_checkoutOrder_da5a27f172_Test {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private NotificationUtil notificationUtil;

    @Test
    public void testCheckoutOrder() {
        OrderRequest order = new OrderRequest();
        order.setPrice(1000);
        order.setDiscountable(true);
        order.setEmailId("test@test.com");

        Mockito.when(notificationUtil.sendEmail(order.getEmailId())).thenReturn("Email sent");

        OrderResponse response = orderService.checkoutOrder(order);
        Assertions.assertEquals(900, response.getOrder().getPrice());
        Assertions.assertEquals("Email sent", response.getMessage());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void testCheckoutOrderWithInvalidEmail() {
        OrderRequest order = new OrderRequest();
        order.setPrice(1000);
        order.setDiscountable(true);
        order.setEmailId("invalid email");

        Mockito.when(notificationUtil.sendEmail(order.getEmailId())).thenReturn("Email not sent");

        OrderResponse response = orderService.checkoutOrder(order);
        Assertions.assertEquals("Email not sent", response.getMessage());
    }

    @Test
    public void testCheckoutOrderWithNegativePrice() {
        OrderRequest order = new OrderRequest();
        order.setPrice(-1000);
        order.setDiscountable(true);
        order.setEmailId("test@test.com");

        OrderResponse response = orderService.checkoutOrder(order);
        Assertions.assertEquals("Price cannot be negative", response.getMessage());
    }

    @Test
    public void testCheckoutOrderWithNullOrder() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            orderService.checkoutOrder(null);
        });
    }

    @Test
    public void testCheckoutOrderWithNullFields() {
        OrderRequest order = new OrderRequest();

        OrderResponse response = orderService.checkoutOrder(order);
        Assertions.assertEquals("Price cannot be null", response.getMessage());
    }
}
