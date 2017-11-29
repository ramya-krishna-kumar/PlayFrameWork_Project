package blog;

import java.util.List;

public interface BlogRepository {




    BlogModel createUser(BlogModel newUser);
    BlogModel usercheck(final String name);
    BlogModel getUser(String email,String blogname);
    BlogModel getlike(String email,String blogname);
    void updateUser(final BlogModel user);
    List<BlogModel> maxlikes();
    List<BlogModel>latestposted();
    void deleteUser(final BlogModel user);

    List<BlogModel> viewCommentsByUserIds(String userIds);
}
