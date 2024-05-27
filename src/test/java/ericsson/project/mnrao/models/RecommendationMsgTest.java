package ericsson.project.mnrao.models;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationMsgTest {

    private static final Long DEFAULT_ID = 1L;
    private static final int DEFAULT_WARNING_TYPE = 2;
    private static final String DEFAULT_DESCRIPTION = "Test Description";
    private static final String DEFAULT_RECOMMENDATION = "Test Recommendation";

    private static final Long UPDATED_ID = 2L;
    private static final int UPDATED_WARNING_TYPE = 3;
    private static final String UPDATED_DESCRIPTION = "Updated Description";
    private static final String UPDATED_RECOMMENDATION = "Updated Recommendation";

    private RecommendationMsg recommendationMsg;

    @BeforeEach
    void setUp() {
        recommendationMsg = new RecommendationMsg(DEFAULT_WARNING_TYPE, DEFAULT_DESCRIPTION, DEFAULT_RECOMMENDATION);
        recommendationMsg.setId(DEFAULT_ID);
    }

    @Test
    void testDefaultConstructor() {
        RecommendationMsg defaultMsg = new RecommendationMsg();
        assertNotNull(defaultMsg);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(DEFAULT_WARNING_TYPE, recommendationMsg.getWarningType());
        assertEquals(DEFAULT_DESCRIPTION, recommendationMsg.getDescription());
        assertEquals(DEFAULT_RECOMMENDATION, recommendationMsg.getRecommendation());
    }

    @Test
    void testSettersAndGetters() {
        recommendationMsg.setId(UPDATED_ID);
        assertEquals(UPDATED_ID, recommendationMsg.getId());

        recommendationMsg.setWarningType(UPDATED_WARNING_TYPE);
        assertEquals(UPDATED_WARNING_TYPE, recommendationMsg.getWarningType());

        recommendationMsg.setDescription(UPDATED_DESCRIPTION);
        assertEquals(UPDATED_DESCRIPTION, recommendationMsg.getDescription());

        recommendationMsg.setRecommendation(UPDATED_RECOMMENDATION);
        assertEquals(UPDATED_RECOMMENDATION, recommendationMsg.getRecommendation());
    }
}

