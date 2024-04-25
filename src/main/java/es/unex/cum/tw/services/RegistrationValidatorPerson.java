package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public interface RegistrationValidatorPerson extends Function<User, ValidatorResult> {
    static RegistrationValidatorPerson isNameValid() {
        return person -> {
            String name = person.getUsername();
            if(name == null || name.isBlank()) {
                return ValidatorResult.INVALID_NAME;
            }else{
                // Verificar que el nombre contenga letras y números, y que no contenga caracteres especiales y que el primer caracter no sea un numero o caracter especial
                if(!name.matches("^[a-zA-Z0-9][a-zA-Z0-9 ]*$")){
                    return ValidatorResult.INVALID_NAME;
                }

            }

            return ValidatorResult.SUCCESS;
        };
    }

    static RegistrationValidatorPerson isPasswordValid() {
        return person -> {
            String password = person.getPassword();
            if(password == null || password.isBlank()) {
                return ValidatorResult.INVALID_PASSWORD;
            }else{
                // Verificar que la contraseña contenga al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número
                if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
                    return ValidatorResult.INVALID_PASSWORD;
                }

            }

            return ValidatorResult.SUCCESS;
        };
    }



    default RegistrationValidatorPerson and(RegistrationValidatorPerson other) {
        return person -> {
            ValidatorResult result = this.apply(person);
            return result.equals(ValidatorResult.SUCCESS) ? other.apply(person) : result;
        };
    }


}

enum ValidatorResult {
    SUCCESS,
    INVALID_NAME,
    INVALID_PASSWORD
}
