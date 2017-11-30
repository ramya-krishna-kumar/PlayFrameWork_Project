package loginprocess;

public interface LoginProcessRepository {




   LoginProcessModel createUser(LoginProcessModel newUser);
   LoginProcessModel getUserByUserid(final String name);
   LoginProcessModel getsignin(String userid, String password);


   LoginProcessModel getUser(String email);


}
