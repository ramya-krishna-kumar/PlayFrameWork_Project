package blog;

import global.exceptions.CustomException;
import global.utils.Helper;
import org.bson.types.ObjectId;
import sam.BlogRequestForm;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.lang.String;

import static global.utils.Helper.*;


@Singleton
public class BlogService {

    private final BlogRepository repository;

    @Inject
    public BlogService(final BlogRepository repository) {
        this.repository = repository;
    }

    public BlogModel postblog(BlogRequestForm userForm) {
        final BlogModel newUser = new BlogModel();
        newUser.setUserid(userForm.getUserid());
        newUser.setBlogname(userForm.getBlogname());
        newUser.setBlogdescription(userForm.getBlogdescription());
        newUser.setLike(userForm.getLike());
        newUser.setComment(userForm.getComment());


        return repository.createUser(newUser);
    }

    public BlogModel getUser(String name) {
        return repository.getUserByUserid(name);
    }



    public BlogModel updateblog(BlogRequestForm newform) {

         BlogModel user = repository.getUser11(newform.getUserid());

        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        user.setUserid(newform.getUserid());
        user.setBlogname(newform.getBlogname());
        user.setBlogdescription(newform.getBlogdescription());
        user.setComment(newform.getComment());

        repository.updateUser(user);

        return user;
    }
    public BlogModel getlike(String email,String blogname)
    {
        BlogModel user= repository.getlike(email,blogname);
        if(user != null) {
            user.setLike(user.getLike() + 1);
            repository.updateUser(user);
            return user;
        }
        return null;

    }

    List<BlogModel> maxlikes()
    {
        return repository.maxlikes();
    }

    public BlogModel postcomments(BlogRequestForm useForm)
    {
        BlogModel user= repository.getlike(useForm.getUserid(),useForm.getBlogname());

       if(user != null) {
            user.setComment(useForm.getComment());
            repository.updateUser(user);
            return user;
        }
        return null;

    }
    List<BlogModel> latestposted()
    {
        return repository.latestposted();


    }

    public List<BlogModel> viewCommentsByserIds(String postedIds) {
        System.out.print(" size  " +repository.viewCommentsByserIds(postedIds).size());
        return repository.viewCommentsByserIds(postedIds);
    }
    public BlogModel deleteblog(BlogRequestForm newform) {

        final BlogModel user = repository.getUserByUserid(newform.getUserid());
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }


        repository.deleteUser(user);

        return user;
    }



}

