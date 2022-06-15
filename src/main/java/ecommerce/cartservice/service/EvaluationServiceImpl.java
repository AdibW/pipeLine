package ecommerce.cartservice.service;

import ecommerce.cartservice.exception.EvaluationNotFoundException;
import ecommerce.cartservice.repository.EvaluationRepository;
import ecommerce.cartservice.model.Evaluation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
    EvaluationRepository evaluationRepository;

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Optional<Evaluation> getEvaluationById(UUID id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluationDto, UUID id) throws EvaluationNotFoundException {
        Optional<Evaluation> evaluation = this.evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            evaluationDto.setId(id);
            return this.evaluationRepository.save(evaluationDto);
        } else {
            throw new EvaluationNotFoundException(id);
        }
    }

    @Override
    public void deleteEvaluation(UUID id) {
        evaluationRepository.deleteById(id);
    }
}
