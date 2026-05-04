package com.mddb.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class PhonesControllerPostgresTest {

    @Container
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void datasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRES::getDriverClassName);
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.liquibase.enabled", () -> "false");
    }

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("delete from device");
        jdbcTemplate.update(
                "insert into device (company_name, model_name) values (?, ?)",
                "Samsung",
                "Galaxy S23"
        );
    }

    @Test
    void getPhones_shouldReturnDeviceFromPostgresContainer() {
        restTestClient.get()
                .uri("http://localhost:%d/phones?pageNumber=1&pageSize=10".formatted(port))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].modelName").isEqualTo("Galaxy S23")
                .jsonPath("$[0].id").exists();
    }
}
