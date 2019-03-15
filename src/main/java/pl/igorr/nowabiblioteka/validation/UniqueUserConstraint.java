package pl.igorr.nowabiblioteka.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueUserValidator.class)  //wskazanie klasy zawierającej warunki walidacji unikalnego użytkownika
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserConstraint { //Nazwa @interfejsu jest jednocześnie nazwą adnotacji w klasie, której pola będą walidowane
	String message() default "user already exist"; //Standardowa wiadomość wyświetlana jako "error" przy nieudanej walidacji
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//reszta kodu jest standardowa przy realizacji dowolnego walidatora