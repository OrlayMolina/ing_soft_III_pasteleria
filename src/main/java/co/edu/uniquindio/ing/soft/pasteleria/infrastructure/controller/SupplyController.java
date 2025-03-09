package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.controller;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplyUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/supplies")
@Tag(name = "Supplies", description = "API para gestionar insumos")
public class SupplyController {

    private final ManageSupplyUseCase supplyUseCase;

    /**
     * Endpoint para la creación de un nuevo insumo.
     * <p>
     * Con este endpoint podrás crear nuevos insumos para la elaboración de pasteles.
     * </p>
     *
     * @param command Objeto {@code CreateSupplyCommand} que contiene la información del insumo a crear.
     * @return {@code ResponseEntity} con el objeto {@code SupplyResponse} y estado HTTP 201 (CREATED).
     * @throws DomainException Si ocurre algún error en la lógica de negocio.
     */
    @PostMapping
    @Operation(summary = "Endpoint para la creación de un nuevo insumo",
            description = "Con este endpoint podrás crear nuevos insumos para la elaboracion de pasteles")
    public ResponseEntity<SupplyResponse> createSupply(@Valid @RequestBody CreateSupplyCommand command) throws DomainException {
        SupplyResponse response = supplyUseCase.createSupply(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * Endpoint para actualizar un insumo existente.
     * <p>
     * Permite modificar la información de un insumo ya registrado.
     * </p>
     *
     * @param id      El ID del insumo a actualizar.
     * @param command Objeto {@code UpdateSupplyCommand} con los nuevos datos a actualizar.
     * @return {@code ResponseEntity} con el objeto {@code SupplyResponse} actualizado y estado HTTP 200 (OK).
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar insumo", description = "Actualiza la información de un insumo existente")
    public ResponseEntity<SupplyResponse> updateSupply(@PathVariable Long id, @Valid @RequestBody UpdateSupplyCommand command) {
        SupplyResponse response = supplyUseCase.updateSupply(id, command);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un insumo.
     * <p>
     * Permite eliminar un insumo identificado por su ID.
     * </p>
     *
     * @param id El ID del insumo a eliminar.
     * @return {@code ResponseEntity} con un mensaje de confirmación y estado HTTP 200 (OK).
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar insumo", description = "Elimina un insumo existente identificado por su ID")
    public ResponseEntity<String> deleteSupply(@PathVariable Long id) {
        supplyUseCase.deleteSupply(id);
        return ResponseEntity.ok().body("Insumo eliminado correctamente");
    }

    /**
     * Endpoint para obtener la información de un insumo.
     * <p>
     * Permite consultar los detalles de un insumo mediante su ID.
     * </p>
     *
     * @param id El ID del insumo a consultar.
     * @return {@code ResponseEntity} con el objeto {@code SupplyResponse} y estado HTTP 200 (OK).
     * @throws DomainException Si el insumo no se encuentra o ocurre algún error.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener insumo", description = "Obtiene la información de un insumo existente mediante su ID")
    public ResponseEntity<SupplyResponse> getSupply(@PathVariable Long id) throws DomainException {
        SupplyResponse response = supplyUseCase.getSupply(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener la lista de todos los insumos.
     * <p>
     * Retorna una lista con todos los insumos registrados.
     * </p>
     *
     * @return {@code ResponseEntity} con la lista de {@code SupplyResponse} y estado HTTP 200 (OK).
     */
    @GetMapping
    @Operation(summary = "Listar insumos", description = "Obtiene una lista de todos los insumos registrados")
    public ResponseEntity<List<SupplyResponse>> getAllSupplies() {
        List<SupplyResponse> supplies = supplyUseCase.searchSupply();
        return ResponseEntity.ok(supplies);
    }

    /**
     * Controlador de excepciones para manejar {@code DomainException} en este controlador.
     * <p>
     * Cuando se lanza una {@code DomainException}, este método captura la excepción y retorna
     * un mensaje de error con estado HTTP 400 (BAD_REQUEST).
     * </p>
     *
     * @param ex La {@code DomainException} lanzada.
     * @return {@code ResponseEntity} con el mensaje de error y estado HTTP 400.
     */

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
