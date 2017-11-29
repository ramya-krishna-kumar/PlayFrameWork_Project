package sam;

import global.exceptions.CustomException;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.lang.String;
import static global.utils.Helper.*;


@Singleton
public class SamService {

    private final SamRepository repository;

    @Inject
    public SamService(final SamRepository repository) {
        this.repository = repository;
    }


/*

  */
/*  public SamModel getUser(ObjectId userId) {
        return repository.getUser(userId);
    }*//*


   */
/* boolean deleteUser(ObjectId userId) {
        final SamModel user = repository.getUser(userId);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        return repository.deleteUser(userId);
    }*//*


   public boolean signupUser(String userid)
   {
       return repository.signupUser(Userid);
   }

*/

    public Optional<SamModel> signinUser(SamRequestForm mForm) {
        final SamModel user = repository.getUser(mForm.getUserid());
        boolean flag = compare(mForm.getPassword(), user.getPassword());

        if (flag)
            return Optional.of(user);
        else
            return Optional.empty();

    }
    public SamModel createUser(SamRequestForm userForm) {
        final SamModel newUser = new SamModel();
        newUser.setUserid(userForm.getUserid());
        newUser.setPassword(hash(userForm.getPassword()));
        newUser.setUsername(userForm.getUsername());


        return repository.createUser(newUser);
    }

   /* public SamModel postblog(SamRequestForm userForm) {
        final SamModel newUser = new SamModel();
        newUser.setUserid(userForm.getUserid());
        newUser.setBlogname(userForm.getBlogname());
        newUser.setBlogdescription(userForm.getBlogdescription());
        newUser.setLike(userForm.getLike());
        newUser.setComment(userForm.getComment());

        return repository.createUser(newUser);
    }*/


    public SamModel getUser(String name) {
        return repository.getUserByUserid(name);
    }

    public SamModel signinUser(String userid, String password) {
        return repository.getsignin(userid, password);
    }

    /*public SamModel updateUser(SamRequestForm newform) {

        final SamModel user = repository.getUser(newform.getUserid());
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        user.setUserid(newform.getUserid());
        user.setPassword(newform.getPassword());
        user.setUsername(newform.getUsername());
        user.setBlogname(newform.getBlogname());
        user.setBlogdescription(newform.getBlogdescription());
        user.setComment(newform.getComment());


        repository.updateUser(user);

        return user;
    }

    public SamModel updateblog(SamRequestForm newform) {

        final SamModel user = repository.getUser(newform.getUserid());

        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        final SamModel a = repository.getLikefield();
        user.setLike(a.getLike() + 1);
        user.setComment(newform.getComment());

        repository.updateUser(user);

        return user;
    }


    public SamModel deleteUser(SamRequestForm newform) {
        // String email      = newform.getUserid();

        final SamModel user = repository.getUser(newform.getUserid());
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }


        repository.deleteUser(user);

        return user;
    }*/


}
    /*public SamModel updateUser(ObjectId userId, SamRequestForm userForm) {
        final SamModel user = repository.getUser(userId);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        user.setuserid(userForm.getuserid());
        user.password(userForm.getpassword());


        repository.updateUser(user);

        return user;
    }*/


