import com.google.inject.AbstractModule;
import global.configuration.db.mongodb.MongoDBConnection;
import user.UserRepository;
import user.UserRepositoryImpl;
import loginprocess.LoginProcessRepository;
import loginprocess.LoginProcessRepositoryImpl;
import blog.BlogRepository;
import blog.BlogRepositoryImpl;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 * <p>
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(MongoDBConnection.class).asEagerSingleton();
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(LoginProcessRepository.class).to(LoginProcessRepositoryImpl.class);
        bind(BlogRepository.class).to(BlogRepositoryImpl.class);
    }

}
