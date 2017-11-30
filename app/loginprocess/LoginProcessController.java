package loginprocess;

import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;

import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;



import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;



@Singleton
public final class LoginProcessController extends BaseController/**basecontroller--nothing but the controller class to the mvc models */
{


    private final LoginProcessService loginservice;


    @Inject
    private LoginProcessController(LoginProcessService loginservice) {
        this.loginservice = loginservice;
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        try {
            final Form<LoginProcessRequestForm> loginModelForm = formFactory.form(LoginProcessRequestForm.class).bindFromRequest();
            if (loginModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(loginModelForm.allErrors()));
            }
            final LoginProcessRequestForm loginForm = loginModelForm.get();
            final LoginProcessModel user = this.loginservice.getUser(loginForm.getUserid());
            if (user != null) {
                return success("user already exists" + loginForm.getUserid());
            } else {
                final LoginProcessModel usercheck = this.loginservice.createUser(loginForm);
                return usercheck != null ? success("successfully created user with ID: " + loginForm.getUserid()) : failure("user already exists");
            }
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }


    public Result signinUser() {
        try {
            final Form<LoginProcessRequestForm> loginModelForm = formFactory.form(LoginProcessRequestForm.class).bindFromRequest();

            final LoginProcessRequestForm loginForm = loginModelForm.get();
            Optional <LoginProcessModel> temp = this.loginservice.signinUser(loginForm);
            if (temp!=null) {
                LoginProcessModel loginprocessModel = temp.get();
                String session = sessionService.generateSession();
                boolean status = sessionService.assignSessionToUser(loginprocessModel.getId(), session,loginForm);

                return status ? success("successfully login", ImmutableMap.of("session", session)) : failure("failed to login");
            } else {
                return failure("Invalid Login credentials");
            }


        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }



   public Result logoutUser(String sessionToken) {
       System.out.print("Test   " + sessionToken );
       boolean status = this.sessionService.deleteSession(sessionToken);
       return status ? success("You've been successfully logged out") : failure("not logout");
   }

}





