package ericsson.project.mnrao.repos;

import ericsson.project.mnrao.models.RecommendationMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedMsgRepo extends JpaRepository<RecommendationMsg, Long> {

    List<Object []> findAllByNodeIdAndResource(int nodeId, String resource);
}
