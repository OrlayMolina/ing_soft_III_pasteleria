package co.edu.uniquindio.ing.soft.pasteleria;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.generateRandomNumericId;
import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.getRandomElement;

@SpringBootTest
public class SupplierServiceTests {

    @Autowired
    private ManageSupplierUseCase manageSupplierUseCase;
    @Autowired
    private ManageUserUseCase manageUserUseCase;
    @Autowired
    private UserJpaRepository jpaRepository;

    @Test
    public void createSupplierTest() throws DomainException {
        String randomSupplierID = generateRandomNumericId(9);
        List<UserEntity> users = jpaRepository.findAll();
        UserEntity user = new UserEntity();
         if(!users.isEmpty()){
             user = getRandomElement(users);
         }

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

            Optional<UserEntity> usersGenerate = jpaRepository.findByDocumentNumber(commandUser.documentNumber());
            UserEntity userGenerate = getRandomElement(Collections.singletonList(usersGenerate.get()));

            CreateSupplierCommand command = new CreateSupplierCommand(
                    "Harina as de oros",
                    randomSupplierID,
                    "Huila",
                    "+577425689",
                    "asdeoros@gmail.com",
                    Status.ACTIVO,
                    LocalDateTime.of(2023, 9, 14, 15, 30, 0),
                    LocalDateTime.of(2023, 9, 14, 15, 30, 0),
                    userGenerate.getId()
            );

            SupplierResponse response = manageSupplierUseCase.createSupplier(command);
            Assertions.assertNotNull(response);
        }else {
            CreateSupplierCommand command = new CreateSupplierCommand(
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

            SupplierResponse response = manageSupplierUseCase.createSupplier(command);
            Assertions.assertNotNull(response);
        }

    }
}
