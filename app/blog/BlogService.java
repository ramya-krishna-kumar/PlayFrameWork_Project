package blog;

import global.exceptions.CustomException;
import global.utils.Helper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.lang.String;


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
        newUser.setCreatedAt(Helper.currentEpoch());
        System.out.print("time " + Helper.currentEpoch( ));
        return repository.createUser(newUser);
    }

    public BlogModel blogpost(String name) {
        return repository.usercheck(name);
    }
    public BlogModel updateblog(BlogRequestForm newform) {

         BlogModel user = repository.usercheck(newform.getUserid());

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

    public List<BlogModel> viewCommentsByUserIds(String postedIds) {
        System.out.print(" size  " +repository.viewCommentsByUserIds(postedIds).size());
        return repository.viewCommentsByUserIds(postedIds);
    }
    public BlogModel deleteblog(BlogRequestForm newform) {

        final BlogModel user = repository.usercheck(newform.getUserid());
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }
        repository.deleteUser(user);

        return user;
    }



}

