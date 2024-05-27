package ericsson.project.mnrao.models;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationMsgTest {

    private static final long DEFAULT_ID = 1L;
    private static final int DEFAULT_NODEID = 1;
    private final WARNINGTYPE DEFAULT_WARNING_TYPE = ericsson.project.mnrao.models.WARNINGTYPE.WARNING;
    private static final String DEFAULT_RECOMMENDATION = "Test Recommendation";
    private final RESOURCE DEFAULT_RESOURCE_TYPE = ericsson.project.mnrao.models.RESOURCE.CPU;

    private static final long UPDATED_ID = 2L;
    private static final int UPDATED_NODEID = 2;
    private final WARNINGTYPE UPDATED_WARNING_TYPE = ericsson.project.mnrao.models.WARNINGTYPE.DANGER;
    private static final String UPDATED_RECOMMENDATION = "Updated Recommendation";
    private final RESOURCE UPDATED_RESOURCE_TYPE = ericsson.project.mnrao.models.RESOURCE.MEMORY;

    private RecommendationMsg recommendationMsg;

    @BeforeEach
    void setUp() {
        recommendationMsg = new RecommendationMsg(DEFAULT_NODEID, DEFAULT_WARNING_TYPE, DEFAULT_RECOMMENDATION, DEFAULT_RESOURCE_TYPE);
    }

    @Test
    void testDefaultConstructor() {
        RecommendationMsg defaultMsg = new RecommendationMsg();
        assertNotNull(defaultMsg);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(DEFAULT_WARNING_TYPE.toString(), recommendationMsg.getWarningType());
        assertEquals(DEFAULT_RECOMMENDATION, recommendationMsg.getRecommendation());
    }

    @Test
    void testSettersAndGetters() {
        recommendationMsg.setId(UPDATED_ID);
        assertEquals(UPDATED_ID, recommendationMsg.getId());

        recommendationMsg.setWarningType(UPDATED_WARNING_TYPE);
        assertEquals(UPDATED_WARNING_TYPE.toString(), recommendationMsg.getWarningType());

        recommendationMsg.setRecommendation(UPDATED_RECOMMENDATION);
        assertEquals(UPDATED_RECOMMENDATION, recommendationMsg.getRecommendation());
    }
}

