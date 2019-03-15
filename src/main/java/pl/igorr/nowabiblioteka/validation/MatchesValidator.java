package pl.igorr.nowabiblioteka.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;



public class MatchesValidator implements ConstraintValidator<MatchesConstraint, Object> {

	private String firstField;
	private String secondField;
    private String message;

	@Override
	public void initialize(MatchesConstraint fields) { // inicjalizacja walidatora
		this.firstField = fields.firstField();
		this.secondField = fields.secondField();
		this.message = fields.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) { // metoda definiująca warunki sprawdzenia
		boolean valid = true;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstField); //pobranie pierwszego pola do sprawdzenia
			final Object secondObj = BeanUtils.getProperty(value, secondField); //pobranie drugiego pola do sprawdzania

			valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj); //sprawdzenie czy oba pola nie są puste i mają tą samą wartość
		} catch (final Exception ignore) {
			//zignorowanie wyjątków a BeanUtils
		}
		
		if (!valid){
            context.buildConstraintViolationWithTemplate(message) //zwrócenie treści wiadomości o błędzie do wyświetlenia
                    .addPropertyNode(firstField) //wskazanie pola, którego ma dotyczyć błąd
                    .addConstraintViolation() //ustawienie naruszenia poprawności danych
                    .disableDefaultConstraintViolation(); //wyłącznie standardowego naruszenia
            context.buildConstraintViolationWithTemplate(message) //jak powyżej dla drugiego pola
            		.addPropertyNode(secondField) 
            		.addConstraintViolation()
            		.disableDefaultConstraintViolation();
        }
		
		return valid; //zwrócenie wartości sprawdzenia
	}

}
