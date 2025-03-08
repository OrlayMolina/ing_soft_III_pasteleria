package co.edu.uniquindio.ing.soft.pasteleria;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.generateRandomNumericId;

@SpringBootTest
public class SupplierServiceTests {

    @Autowired
    private ManageSupplierUseCase manageSupplierUseCase;

    @Test
    public void createSupplier() throws DomainException {
        String randomSupplierID = generateRandomNumericId(9);

        CreateSupplierCommand command = new CreateSupplierCommand(
                "Harina as de oros",
                randomSupplierID,
                "Huila",
                "+577425689",
                "asdeoros@gmail.com",
                Status.ACTIVO,
                LocalDateTime.of(2023, 9, 14, 15, 30, 0),
                LocalDateTime.of(2023, 9, 14, 15, 30, 0)
        );

        SupplierResponse response = manageSupplierUseCase.createSupplier(command);
        Assertions.assertNotNull(response);
    }
}
