package com.udemy.dropbookmarks.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloResourceTest {

    @ClassRule
    public static final ResourceTestRule RULE
            = ResourceTestRule
            .builder()
            .addResource(new HelloResource())
            .build();

    @Test
    public void testGreeting() {
        String expectedMessage = "Hello World!";
        String actualMessage = RULE
                .getJerseyTest()
                .target("/hello")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        assertEquals(expectedMessage, actualMessage);
    }

}
