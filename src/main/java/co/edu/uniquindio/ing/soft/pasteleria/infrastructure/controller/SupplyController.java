package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.controller;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplyUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplies")
@AllArgsConstructor
public class SupplyController {

    private final ManageSupplyUseCase supplyUseCase;

    @PostMapping
    public ResponseEntity<SupplyResponse> createSupply(@Valid @RequestBody CreateSupplyCommand command) throws DomainException {
        SupplyResponse response = supplyUseCase.createSupply(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyResponse> updateSupply(@PathVariable Long id, @Valid @RequestBody UpdateSupplyCommand command) {
        SupplyResponse response = supplyUseCase.updateSupply(id, command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupply(@PathVariable Long id) {
        supplyUseCase.deleteSupply(id);
        return ResponseEntity.ok().body("Insumo eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyResponse> getSupply(@PathVariable Long id) throws DomainException {
        SupplyResponse response = supplyUseCase.getSupply(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplyResponse>> getAllSupplies() {
        List<SupplyResponse> supplies = supplyUseCase.searchSupply();
        return ResponseEntity.ok(supplies);
    }

    // Opcionalmente, podrías agregar un controlador de excepciones específico para este controlador
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
