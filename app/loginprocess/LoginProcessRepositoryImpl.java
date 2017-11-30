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


}