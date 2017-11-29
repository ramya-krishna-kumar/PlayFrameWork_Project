package loginprocess;

import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class LoginProcessRepositoryImpl extends BaseRepository<LoginProcessModel> implements LoginProcessRepository {

    @Inject
    public LoginProcessRepositoryImpl(MongoDBConnection databaseConnection) {
        super(LoginProcessModel.class, databaseConnection);
    }


 /* *//*  @Override
    public LoginProcessModel getUserByuserid(final String userid) {
        return query()
                .field(LoginProcessModel.Fields.userid.name())
                .equal(userid)
                .get();
    }

   @Override
    public LoginProcessModel getUserBypassword(final String password) {
        return query()
                .field(LoginProcessModel.Fields.password.name())
                .equal(password)
                .get();
    }



    @Override
    public boolean deleteUser(ObjectId userId) {
        return delete(userId);
    }

    @Override
    public void updateUser(LoginProcessModel user) {
        update(user);
    }

    @Override
    public LoginProcessModel getUser(String userid, String password) {
        return query()
                .field(LoginProcessModel.Fields.userid.name())
                .equal(userid)
                .field(LoginProcessModel.Fields.password.name())
                .equal(password)
                .get();
    }*//*
     @Override
    public boolean signupUser(String userid) {
        if(
                query()
                .field(LoginProcessModel.Fields.userid.name())
                .equals(userid))
            return true;
        else
            return false;


    }

*/


    @Override
    public LoginProcessModel createUser(LoginProcessModel newUser) {

        create(newUser);
        return newUser;
    }

    @Override
    public LoginProcessModel getUserByUserid(final String name) {
        return query()
                .field(LoginProcessModel.Fields.userid.name())
                .equal(name)
                .get();
    }

    @Override
    public LoginProcessModel getsignin(final String userid, final String password) {
        return query()
                .field(LoginProcessModel.Fields.userid.name())
                .equal(userid)
                .field(LoginProcessModel.Fields.password.name())
                .equal(password)
                .get();
    }

    @Override
    public LoginProcessModel getUser(String email) {
        return query()
                .field(LoginProcessModel.Fields.userid.name())
                .equal(email)
                .get();


    }

   /* public LoginProcessModel getLikefield() {
        return query()
                .field(LoginProcessModel.Fields.like.name())
                .greaterThanOrEq(0)
                .get();//like:0 update/like:1
    }

*/




  /*  @Override
    public void updateUser(LoginProcessModel user) {
        update(user);
    }


    @Override
    public void deleteUser(LoginProcessModel user) {
        delete(user);
    }

}*/
}