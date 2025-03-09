package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter.config;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserJpaRepository usuarioRepository;

    @Autowired
    private ManageUserUseCase manageUserUseCase;

    @Autowired
    private ManageSupplierUseCase manageSupplierUseCase;

    @Override
    public void run(String... args) {
        try {
            initializeAdminUser();

            initializeSuppliers();

            logger.info("Data initialization completed successfully");
        } catch (Exception e) {

            logger.error("Error during data initialization: {}", e.getMessage(), e);
        }
    }

    private void initializeAdminUser() {
        try {
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
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null
                );
                manageUserUseCase.createUser(command);
                logger.info("Admin user created successfully");
            } else {
                logger.info("Admin user already exists, skipping creation");
            }
        } catch (Exception e) {
            logger.error("Error creating admin user: {}", e.getMessage());
        }
    }

    private void initializeSuppliers() {
        try {
            createSupplierIfNotExists("Harina as de oros", "asdeoros@gmail.com", "Huila", "+577425689");
            createSupplierIfNotExists("Huevos Santa Reyes", "contacto@santareyes.com", "Bogotá", "+5716467878");
            createSupplierIfNotExists("Leche Alquería", "info@alqueria.com.co", "Medellín", "+5743609080");
            createSupplierIfNotExists("Azúcar Manuelita", "servicioalcliente@manuelita.com", "Cali", "+5723310999");
        } catch (Exception e) {
            logger.error("Error creating suppliers: {}", e.getMessage());
        }
    }

    private void createSupplierIfNotExists(String name, String email, String location, String phone) {
        try {

            String randomSupplierId = generateRandomNumericId(9);
            CreateSupplierCommand command = new CreateSupplierCommand(
                    name,
                    randomSupplierId,
                    location,
                    phone,
                    email,
                    Status.ACTIVO,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L
            );
            manageSupplierUseCase.createSupplier(command);
            logger.info("Supplier {} created successfully", name);

        } catch (Exception e) {
            logger.warn("Could not create supplier {}: {}", name, e.getMessage());

        }
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