package userOperations.core.concretes;

import userOperations.core.abstracts.EmailValidationServices;
import userOperations.entities.concretes.User;

public class EmailValidationManager implements EmailValidationServices {

	@Override
	public void sendValidationEmailForUser(User user) {
		
		System.out.println(user.getEmail() + " adresinize do�rulama maili g�nderildi.");	
        
        System.out.println(user.getEmail() + " adresinize g�nderilen do�rulama mailine t�klay�n�z.");		
	}

}
