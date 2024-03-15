package com.andersenlab.hoovercontrol.end2end;

import com.andersenlab.hoovercontrol.domain.Result;
import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.dto.ResponseDto;
import com.andersenlab.hoovercontrol.repository.ResultRepository;
import com.andersenlab.hoovercontrol.util.ObjectCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "integration_test")
public class IntegrationTest extends TestContainerConfiguration {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ResultRepository resultRepository;

    @Test
    @DisplayName("Positive test for sending task and receiving a result with checking storing in DB")
    void TestForSendingTaskThenReceivingResultThenCheckingStoringInDb() {

        HttpEntity<RequestDto> request = new HttpEntity<>(ObjectCreator.createDto());
        ResponseEntity<ResponseDto> response = testRestTemplate.postForEntity("/hoovers/tasks", request, ResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCoords()[0]);
        assertEquals(1, response.getBody().getCoords()[1]);
        assertEquals(1, response.getBody().getPatches());

        List<Result> resultList = resultRepository.findAll();
        assertEquals(1, resultList.size());
        Result result = resultList.get(0);
        assertEquals(response.getBody().getId(), result.getId().longValue());
        assertEquals(1, result.getCoordinateX());
        assertEquals(1, result.getCoordinateY());
        assertEquals(1, result.getPatches());
    }
}
