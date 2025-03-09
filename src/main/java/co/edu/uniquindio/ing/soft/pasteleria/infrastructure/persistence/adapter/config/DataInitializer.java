package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter.config;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserJpaRepository usuarioRepository;

    @Autowired
    private ManageUserUseCase manageUserUseCase;

    @Autowired
    private ManageSupplierUseCase manageSupplierUseCase;

    @Override
    public void run(String... args) throws DomainException {
        if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
            String randomUserDocument = generateRandomNumericId(10);
            CreateUserCommand command = new CreateUserCommand(
                    TypeDocument.CC,
                    randomUserDocument,
                    "3104859632",
                    "Auxiliar",
                    1200000F,
                    "Carolina",
                    null,
                    "Villanueva",
                    null,
                    "carolina@gmail.com",
                    "123456",
                    Status.ACTIVO,
                    true,
                    LocalDateTime.of(2024, 9, 14, 15, 30, 0),
                    LocalDateTime.of(2024, 9, 14, 15, 30, 0),
                    null
            );

            manageUserUseCase.createUser(command);
        }

        String randomSupplierID1 = generateRandomNumericId(9);

        CreateSupplierCommand command = new CreateSupplierCommand(
                "Harina as de oros",
                randomSupplierID1,
                "Huila",
                "+577425689",
                "asdeoros@gmail.com",
                Status.ACTIVO,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L
        );

        manageSupplierUseCase.createSupplier(command);

        String randomSupplierID2 = generateRandomNumericId(9);

        CreateSupplierCommand huevosSupplier = new CreateSupplierCommand(
                "Huevos Santa Reyes",
                randomSupplierID2,
                "Bogotá",
                "+5716467878",
                "contacto@santareyes.com",
                Status.ACTIVO,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L
        );

        manageSupplierUseCase.createSupplier(huevosSupplier);

        String randomSupplierID3 = generateRandomNumericId(9);

        CreateSupplierCommand lecheSupplier = new CreateSupplierCommand(
                "Leche Alquería",
                randomSupplierID3,
                "Medellín",
                "+5743609080",
                "info@alqueria.com.co",
                Status.ACTIVO,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L
        );

        manageSupplierUseCase.createSupplier(lecheSupplier);

        String randomSupplierID4 = generateRandomNumericId(9);

        CreateSupplierCommand azucarSupplier = new CreateSupplierCommand(
                "Azúcar Manuelita",
                randomSupplierID4,
                "Cali",
                "+5723310999",
                "servicioalcliente@manuelita.com",
                Status.ACTIVO,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L
        );

        manageSupplierUseCase.createSupplier(azucarSupplier);

    }

    private String generateRandomNumericId(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}

