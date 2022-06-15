package ecommerce.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Evaluation {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        private Double noteCV;
        private Double noteRH;
        private Double noteTechnique;
        private String description;

}
