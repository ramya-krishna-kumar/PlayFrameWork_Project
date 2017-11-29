package sam;

import global.common.BaseModel;
import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton
public class SamRepositoryImpl extends BaseRepository<SamModel> implements SamRepository {

    @Inject
    public SamRepositoryImpl(MongoDBConnection databaseConnection) {
        super(SamModel.class, databaseConnection);
    }


 /* *//*  @Override
    public SamModel getUserByuserid(final String userid) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(userid)
                .get();
    }

   @Override
    public SamModel getUserBypassword(final String password) {
        return query()
                .field(SamModel.Fields.password.name())
                .equal(password)
                .get();
    }



    @Override
    public boolean deleteUser(ObjectId userId) {
        return delete(userId);
    }

    @Override
    public void updateUser(SamModel user) {
        update(user);
    }

    @Override
    public SamModel getUser(String userid, String password) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(userid)
                .field(SamModel.Fields.password.name())
                .equal(password)
                .get();
    }*//*
     @Override
    public boolean signupUser(String userid) {
        if(
                query()
                .field(SamModel.Fields.userid.name())
                .equals(userid))
            return true;
        else
            return false;


    }

*/


    @Override
    public SamModel createUser(SamModel newUser) {

        create(newUser);
        return newUser;
    }

    @Override
    public SamModel getUserByUserid(final String name) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(name)
                .get();
    }

    @Override
    public SamModel getsignin(final String userid, final String password) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(userid)
                .field(SamModel.Fields.password.name())
                .equal(password)
                .get();
    }

    @Override
    public SamModel getUser(String email) {
        return query()
                .field(SamModel.Fields.userid.name())
                .equal(email)
                .get();


    }

   /* public SamModel getLikefield() {
        return query()
                .field(SamModel.Fields.like.name())
                .greaterThanOrEq(0)
                .get();//like:0 update/like:1
    }

*/




  /*  @Override
    public void updateUser(SamModel user) {
        update(user);
    }


    @Override
    public void deleteUser(SamModel user) {
        delete(user);
    }

}*/
}