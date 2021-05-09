package userOperations.business.concretes;

import userOperations.business.abstracts.UserService;
import userOperations.core.abstracts.EmailValidationServices;
import userOperations.core.abstracts.LoginControllerServices;
import userOperations.core.abstracts.jOutsideServices;
import userOperations.dataAccess.abstracts.UserDao;
import userOperations.entities.concretes.User;

public class UserManager implements UserService{
	
	private LoginControllerServices loginControllerServices;
	private UserDao userDao;
	private EmailValidationServices emailVerificationServices;
	private jOutsideServices jOutSideServices;
	
	public UserManager(
			LoginControllerServices loginControllerServices,
			UserDao userDao,
			EmailValidationServices emailVerificationServices,
			jOutsideServices jOutSideServices) 
	{	
		this.loginControllerServices = loginControllerServices;
		this.userDao=userDao;
		this.emailVerificationServices=emailVerificationServices;
		this.jOutSideServices=jOutSideServices;
	}
	

	@Override
	public void register(User user) {
		
		this.checkMail(user.getEmail());
		
        if(!loginControllerServices.isValidEmail(user.getEmail())) {
			return;
 		}
		System.out.println("Email kontrol� ba�ar�l�!");

		if(!loginControllerServices.isValidPassword(user.getPassword())){
			return;
		}
		System.out.println("Parola kontrol� ba�ar�l�!");
		if(!loginControllerServices.isValidFirstName(user.getFirstName())) {
			return;
		}
		System.out.println("�sim kontrol� ba�ar�l�!");

		if(!loginControllerServices.isValidLastName(user.getLastName())) {
			return;
		}
		System.out.println("Soyisim kontrol� ba�ar�l�!");

		userDao.add(user);
		emailVerificationServices.sendValidationEmailForUser(user);
		System.out.println("Say�n " + user.getFirstName() +" " + user.getLastName() + " Kay�t i�leminiz ba�ar� ile ger�ekle�mi�tir.");
		jOutSideServices.registerWith();
	}

	@Override
	public void login(String email, String password) {
		
		if(!loginControllerServices.isValidEmail(email)) {
			return;
		}
		if(!loginControllerServices.isValidPassword(password)) {
			return;
		}
		for(User user : userDao.getAll()) {
			if( user.getEmail() == email && user.getPassword() == password) {
				System.out.println("Sisteme giri� ba�ar�l�");
				return;
			}
			else {
				System.out.println("Giri� ba�ar�s�z! Hatal� Email yada parola girdiniz! ");
			}
		}
	}

	@Override
	public void checkMail(String email) {
		for(User user : userDao.getAll()) {
	    	  if(user.getEmail() == email) {
	             throw new ArithmeticException("E-Posta adresi ile kay�t zaten mevcut");  		  
	    	  }
	      }		
		}

}
