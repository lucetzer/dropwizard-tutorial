package com.udemy.dropbookmarks;

import com.udemy.dropbookmarks.DropBookmarksApplication;
import com.udemy.dropbookmarks.DropBookmarksConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


public class AuthIntegrationTest {

    private static final String CONFIG_PATH = "config.yml";

    @ClassRule
    public static final DropwizardAppRule<DropBookmarksConfiguration> RULE
            = new DropwizardAppRule<> (DropBookmarksApplication.class,
            CONFIG_PATH);

    private static final HttpAuthenticationFeature FEATURE = HttpAuthenticationFeature.basic("username", "p@ssw0rd");

    private static final String TARGET = "http://localhost:8080";

    private static final String SECURE_TARGET = "https://localhost:8443";

    private static final String PATH = "/hello/secured";

    private static final String TRUST_STORE_FILE_NAME = "dropbookmarks.keystore";

    private static final String TRUST_STORE_PASSWORD = "p@ssw0rd";

    private Client client;

    @Before
    public void setUp() {
        SslConfigurator configurator = SslConfigurator.newInstance();
        configurator.trustStoreFile(TRUST_STORE_FILE_NAME).trustStorePassword(TRUST_STORE_PASSWORD);
        SSLContext context = configurator.createSSLContext();
        client = ClientBuilder.newBuilder()
                .sslContext(context)
                .build();
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void testSadPath() {
        Response response = client
                .target(TARGET)
                .path(PATH)
                .request()
                .get();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testHappyPath() {
        String expected = "Hello secured world!";
        client.register(FEATURE);
        String actual = client
                .target(TARGET)
                .path(PATH)
                .request()
                .get(String.class);

        assertEquals(expected, actual);
    }

    @Test
    public void testSadPathSecure() {
        Response response = client
                .target(SECURE_TARGET)
                .path(PATH)
                .request()
                .get();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testHappyPathSecure() {
        String expected = "Hello secured world!";
        client.register(FEATURE);
        String actual = client
                .target(SECURE_TARGET)
                .path(PATH)
                .request()
                .get(String.class);

        assertEquals(expected, actual);
    }

}
