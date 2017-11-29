package blog;

import global.common.BaseModel;
import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import sam.SamModel;


@Singleton
public class BlogRepositoryImpl extends BaseRepository<BlogModel> implements BlogRepository {

    private List<BlogModel> blogModels;

    @Inject
    public BlogRepositoryImpl(MongoDBConnection databaseConnection) {
        super(BlogModel.class, databaseConnection);
    }

    @Override
    public BlogModel createUser(BlogModel newUser) {

        create(newUser);
        return newUser;

    }

    public BlogModel getUserByUserid(final String name) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(name)
                .get();
    }
    public BlogModel getUser11(final String name) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(name)
                .get();
    }

    public BlogModel getlike(String email,String blogname) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(email)
                .field(BlogModel.Fields.blogname.name())
                .equal(blogname)
                .get();
    }

    @Override
    public BlogModel getUser(String email,String blogname1) {
        return query()
                .field(BlogModel.Fields.userid.name())
                .equal(email)
                .field(BlogModel.Fields.blogname.name())
                .equal(blogname1)
                .get();



    }
    @Override
    public void updateUser(BlogModel user) {
        update(user);
    }

    @Override
    public List<BlogModel> maxlikes()
    {
        System.out.print("sizee   " + query().order("-like").asList());
        return query().order("-like").asList();
    }

    @Override
    public List<BlogModel> latestposted()
    {

        return query().order("-CreatedAt").asList();
    }

    @Override
    public List<BlogModel> viewCommentsByserIds(String s1) {
        List<String> userIdArray = Arrays.asList(s1.split(","));
        return  query().field(BlogModel.Fields.userid.name())
                .in(userIdArray)
                .asList();


    }
    @Override
    public void deleteUser(BlogModel user) {
        delete(user);
    }

}
