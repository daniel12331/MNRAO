package ericsson.project.mnrao.models;

import javax.persistence.*;

@Entity
@Table(name = "recommendation_msg")
public class RecommendationMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int nodeId;

    @Column
    private String warningType;

    @Column
    private String recommendation;


    @Column
    private String resource;


    public RecommendationMsg() {

    }

    public RecommendationMsg(int nodeId, WARNINGTYPE warningType, String recommendation, RESOURCE resource) {
        this.nodeId = nodeId;
        this.warningType = warningType.toString();
        this.recommendation = recommendation;
        this.resource = resource.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(WARNINGTYPE warningType) {
        this.warningType = warningType.toString();
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(RESOURCE resource) {
        this.resource = resource.toString();
    }
}
