package ecommerce.cartservice.service;

import ecommerce.cartservice.exception.EvaluationNotFoundException;
import ecommerce.cartservice.model.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface EvaluationService {
     List<Evaluation> getAllEvaluations();
     Optional<Evaluation> getEvaluationById(UUID id);
     Evaluation createEvaluation(Evaluation Evaluation);
     Evaluation updateEvaluation(Evaluation Evaluation, UUID id) throws EvaluationNotFoundException;
     void deleteEvaluation(UUID id);
}
