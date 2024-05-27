package ericsson.project.mnrao.controllers;

import ericsson.project.mnrao.repos.RecommendedMsgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/recommendations")
public class MainController {

    @Autowired
    RecommendedMsgRepo recommendedMsgRepo;


    @GetMapping("/getRecommendations/{nodeId}/{resource}")
    public List<Object[]> getUniqueCauseCodesPerImsi(@PathVariable("nodeId") int nodeId, @PathVariable("resource") String resource){
        List<Object []> result = null;
        result = recommendedMsgRepo.findAllByNodeIdAndResource(nodeId, resource);

        return result;
    }

}
