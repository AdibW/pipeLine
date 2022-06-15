package ecommerce.cartservice.controller;

import ecommerce.cartservice.exception.EvaluationNotFoundException;
import ecommerce.cartservice.model.Evaluation;
import ecommerce.cartservice.service.EvaluationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/evaluations")
@Api(value = "Evaluation API")
@AllArgsConstructor
public class EvaluationController {

	@Autowired
    EvaluationService evaluationService;

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluation() {
        List<Evaluation> evaluations = this.evaluationService.getAllEvaluations();
        if (evaluations.isEmpty()) { 
        	
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(evaluations, HttpStatus.OK);
        }
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable("id") UUID id) {
        Optional<Evaluation> evaluation = this.evaluationService.getEvaluationById(id);
        if (evaluation.isPresent()) {
            return new ResponseEntity<>(evaluation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluationDTO) {
        Evaluation evaluation = this.evaluationService.createEvaluation(evaluationDTO);
        return new ResponseEntity<>(evaluation, HttpStatus.CREATED);

    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable("id") UUID id, @RequestBody Evaluation evaluationDTO) {
        try {
            return new ResponseEntity<>(this.evaluationService.updateEvaluation(evaluationDTO, id), HttpStatus.OK);
        }catch (EvaluationNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvaluation(@PathVariable("id") UUID id) {
        try {
            this.evaluationService.deleteEvaluation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
