package loginprocess;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.lang.String;
import static global.utils.Helper.*;


@Singleton
public class LoginProcessService {

    private final LoginProcessRepository repository;

    @Inject
    public LoginProcessService(final LoginProcessRepository repository) {
        this.repository = repository;
    }

    public Optional<LoginProcessModel> signinUser(LoginProcessRequestForm mForm) {
        final LoginProcessModel user = repository.getUser(mForm.getUserid());
        boolean flag = compare(mForm.getPassword(), user.getPassword());

        if (flag)
            return Optional.of(user);
        else
            return Optional.empty();

    }

    public LoginProcessModel createUser(LoginProcessRequestForm userForm) {
        final LoginProcessModel newUser = new LoginProcessModel();
        newUser.setUserid(userForm.getUserid());
        newUser.setPassword(hash(userForm.getPassword()));
        newUser.setUsername(userForm.getUsername());


        return repository.createUser(newUser);
    }


    public LoginProcessModel getUser(String name) {
        return repository.getUserByUserid(name);
    }

    public LoginProcessModel signinUser(String userid, String password) {
        return repository.getsignin(userid, password);
    }
}

