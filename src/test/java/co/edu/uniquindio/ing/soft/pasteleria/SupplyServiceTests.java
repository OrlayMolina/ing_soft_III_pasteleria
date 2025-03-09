package co.edu.uniquindio.ing.soft.pasteleria;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplyUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplierEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter.config.CryptoPassword.encriptarPassword;
import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.generateRandomNumericId;
import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.getRandomElement;

@SpringBootTest
public class SupplyServiceTests {

    @Autowired
    private SupplierJpaRepository jpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ManageUserUseCase manageUserUseCase;
    @Autowired
    private ManageSupplyUseCase manageSupplyUseCase;


    @Test
    public void createSupplyTest() throws DomainException {
        List<SupplierEntity> suppliers = jpaRepository.findAll();
        SupplierEntity supplier = new SupplierEntity();
        if(!suppliers.isEmpty()){
            supplier = getRandomElement(suppliers);
        }
        List<UserEntity> users = userJpaRepository.findAll();
        UserEntity user = new UserEntity();
        if(!users.isEmpty()){
            user = getRandomElement(users);
        }
        if(supplier == null){
            if(user == null){
                String randomUserDocument = generateRandomNumericId(10);

                CreateUserCommand commandUser = new CreateUserCommand(
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
                UserResponse response = manageUserUseCase.createUser(commandUser);
                Assertions.assertNotNull(response);
                List<UserEntity> usersGenerate = userJpaRepository.findAll();
                UserEntity userGenerate = getRandomElement(usersGenerate);

                String randomSupplierID = generateRandomNumericId(10);
                CreateSupplyCommand supplyCommand = new CreateSupplyCommand(
                        "Azúcar",
                        randomSupplierID,
                        15000.0,
                        LocalDate.now(),
                        LocalDate.now().plusDays(30),
                        100,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        userGenerate.getId()
                );

                SupplyResponse SupplyResponse = manageSupplyUseCase.createSupply(supplyCommand);
                Assertions.assertNotNull(SupplyResponse);
            }else {
                String randomSupplierID = generateRandomNumericId(10);
                CreateSupplierCommand supplierCommand = new CreateSupplierCommand(
                        "Harina as de oros",
                        randomSupplierID,
                        "Huila",
                        "+577425689",
                        "asdeoros@gmail.com",
                        Status.ACTIVO,
                        LocalDateTime.of(2023, 9, 14, 15, 30, 0),
                        LocalDateTime.of(2023, 9, 14, 15, 30, 0),
                        user.getId()
                );
            }
        }else {

            if(user == null){
                String randomUserDocument = generateRandomNumericId(10);

                CreateUserCommand commandUser = new CreateUserCommand(
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
                UserResponse userResponse = manageUserUseCase.createUser(commandUser);
                Assertions.assertNotNull(userResponse);

                List<UserEntity> usersGenerate = userJpaRepository.findAll();
                UserEntity userGenerate = getRandomElement(usersGenerate);

                CreateSupplyCommand command = new CreateSupplyCommand(
                        "Azúcar",
                        supplier.getSupplierID(),
                        15000.0,
                        LocalDate.now(),
                        LocalDate.now().plusDays(30),
                        100,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        userGenerate.getId()
                );

                SupplyResponse SupplyResponse = manageSupplyUseCase.createSupply(command);
                Assertions.assertNotNull(SupplyResponse);
            }
        }
    }
}
