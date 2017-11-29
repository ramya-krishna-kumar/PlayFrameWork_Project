package blog;

import org.bson.types.ObjectId;
import sam.BlogRequestForm;

import java.util.List;

public interface BlogRepository {




    BlogModel createUser(BlogModel newUser);
    BlogModel getUserByUserid(final String name);
    BlogModel getUser(String email,String blogname);
    BlogModel getUser11(String email);
    BlogModel getlike(String email,String blogname);
    void updateUser(final BlogModel user);
    List<BlogModel> maxlikes();
    List<BlogModel>latestposted();
    void deleteUser(final BlogModel user);

    List<BlogModel> viewCommentsByserIds(String userIds);
}
