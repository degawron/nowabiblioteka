package pl.igorr.nowabiblioteka.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MatchesValidator.class)  //wskazanie klasy zawierającej warunki walidacji zgodności wartości z dwóch pól formularza
@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesConstraint { //Nazwa @interfejsu jest jednocześnie nazwą adnotacji w klasie, której pola będą sprawdzane
	String message() default ""; //Standardowa błędu jest nieistotna - zostanie dodana w walidatorze jako zmienna przekazywana w adnotacji
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String firstField();//wartość pierwszego porównywanego pola
    String secondField();//wartość drugiego porównywanego pola
    
    @Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List //obsługa dodania w adnotacji listy porównywanych par pól
    {
    	MatchesConstraint[] value();
    }
}

//reszta kodu jest standardowa przy realizacji dowolnego walidatora