package ericsson.project.mnrao.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import ericsson.project.mnrao.repos.RecommendedMsgRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainControllerTest {

    private RecommendedMsgRepo recommendedMsgRepo;
    private MainController mainController;

    @BeforeEach
    void setUp() {
        recommendedMsgRepo = mock(RecommendedMsgRepo.class);
        mainController = new MainController();
        mainController.recommendedMsgRepo = recommendedMsgRepo;
    }

    @Test
    public void testGetAllRecommendations() {
        List<Object[]> mockResponse = Arrays.asList(
                new Object[]{"cpu"},
                new Object[]{"bandwidth"}
        );
        when(recommendedMsgRepo.findAllByNodeIdAndResource(1, "cpu")).thenReturn(mockResponse);
        List<Object[]> result = mainController.getAllRecommendations(1, "cpu");
        assertEquals(2, result.size());
        assertEquals("cpu", result.get(0)[0]);
        assertEquals("bandwidth", result.get(1)[0]);
    }
}