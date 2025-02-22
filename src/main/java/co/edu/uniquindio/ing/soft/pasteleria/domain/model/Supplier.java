package co.edu.uniquindio.ing.soft.pasteleria.domain.model;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static co.edu.uniquindio.ing.soft.pasteleria.domain.constant.Constants.SUPPLIER_NAME_CAN_NOT_BE_EMPTY;
import static co.edu.uniquindio.ing.soft.pasteleria.domain.constant.Constants.SUPPLIER_NAME_LENGTH;
import static co.edu.uniquindio.ing.soft.pasteleria.domain.constant.Constants.SUPPLIER_ADDRESS_CAN_NOT_BE_EMPTY;

@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    private Long id;
    private String name;
    private String supplierID;
    private String address;
    private String phone;
    private String email;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Supplier(Long id, String name, String supplierID, String address,
                    String phone, String email, Status status, LocalDateTime createdAt,
                    LocalDateTime updatedAt) throws DomainException {
        validateName(name);
        validateAddress(address);

        this.id = id;
        this.name = name;
        this.supplierID = supplierID;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.status = (status == null) ? Status.ACTIVO : status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Supplier activate() throws DomainException {
        if (isActive()) {
            return this;
        }
        return new Supplier(id, name, supplierID, address, phone, email, Status.ACTIVO, createdAt, updatedAt);
    }

    public Supplier deactivate() throws DomainException {
        if (!isActive()) {
            return this;
        }
        return new Supplier(id, name, supplierID, address, phone, email, Status.INACTIVO, createdAt, updatedAt);
    }

    public boolean isActive(){
        return Status.ACTIVO.equals(status);
    }

    private void validateName(String name) throws DomainException {
        if(name == null || name.trim().isEmpty()){
            throw new DomainException(SUPPLIER_NAME_CAN_NOT_BE_EMPTY);
        }
        if(name.length() < 3 || name.length() > 100){
            throw new DomainException(SUPPLIER_NAME_LENGTH);
        }
    }

    private void validateAddress(String address) throws DomainException {
        if(address == null || address.trim().isEmpty()){
            throw new DomainException(SUPPLIER_ADDRESS_CAN_NOT_BE_EMPTY);
        }
    }
}
