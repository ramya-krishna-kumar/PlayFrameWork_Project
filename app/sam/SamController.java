package sam;

import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;
import org.bson.types.ObjectId;

import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;



import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;



@Singleton
public final class SamController extends BaseController/**basecontroller--nothing but the controller class to the mvc models */
{


    private final SamService samservice;


    @Inject
    private SamController(SamService samservice) {
        this.samservice = samservice;
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        try {
            final Form<SamRequestForm> samModelForm = formFactory.form(SamRequestForm.class).bindFromRequest();
            if (samModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(samModelForm.allErrors()));
            }
            final SamRequestForm samForm = samModelForm.get();
            final SamModel temp = this.samservice.getUser(samForm.getUserid());
            if (temp != null) {
                return success("user already exists" + samForm.getUserid());

                //return temp!=null ? success("user already exists" +   samForm.getUserid()) :failure("");
                // return ok("user already exists"+samForm.getUserid());

            } else {
                final SamModel user = this.samservice.createUser(samForm);
                return user != null ? success("successfully created user with ID: " + samForm.getUserid()) : failure("user already exists");
            }
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }
   /* public Result postblog() {
        try {
            final Form<SamRequestForm> samModelForm = formFactory.form(SamRequestForm.class).bindFromRequest();
            if (samModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(samModelForm.allErrors()));
            }
            final SamRequestForm samForm = samModelForm.get();
            final SamModel temp = this.samservice.getUser(samForm.getUserid());
            if (temp != null) {

                final SamModel user = this.samservice.postblog(samForm);
                return user != null ? success("successfully posted your blog " + samForm.getUserid()) : failure("no blog has been posted");
            }


        }
        catch (CustomException e) {
            return failure(e.getMessage());
        }
        return success("sucessfully posted your blog");
    }

*/














    public Result signinUser() {
        try {
            final Form<SamRequestForm> samModelForm = formFactory.form(SamRequestForm.class).bindFromRequest();

            final SamRequestForm samForm = samModelForm.get();
            Optional <SamModel> temp = this.samservice.signinUser(samForm);
            if (temp!=null) {
                SamModel samModel = temp.get();
                String session = sessionService.generateSession();
                boolean status = sessionService.assignSessionToUser(samModel.getId(), session,samForm);

                return status ? success("successfully login", ImmutableMap.of("session", session)) : failure("failed to login");
            } else {
                return failure("Invalid Login credentials");
            }


        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }


   /* public Result updateUser() {
        try {
            final Form<SamRequestForm> ins = formFactory.form(SamRequestForm.class).bindFromRequest();
            if (ins.hasErrors()) {
                return failure(buildValidationErrorMessage(ins.allErrors()));
            }

           *//* if (!ObjectId.isValid(email)) {
                return failure("Invalid User ID type");
            }*//*

            final SamRequestForm newform = ins.get();
            final SamModel user = this.samservice.updateUser(newform);
            return user != null ? success("successfully updated user") : failure("failed to update user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }
    public Result logoutUser(String sessionToken) {
        boolean status = this.sessionService.deleteSession(sessionToken);
        return status ? success("You've been successfully logged out") : failure("not logout");
    }


    public Result blogupdate() {
        try {
            final Form<SamRequestForm> ins = formFactory.form(SamRequestForm.class).bindFromRequest();
            if (ins.hasErrors()) {
                return failure(buildValidationErrorMessage(ins.allErrors()));
            }
            final SamRequestForm newform = ins.get();
            final SamModel user = this.samservice.updateblog(newform);
            return user != null ? success("successfully updated user") : failure("failed to update user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result deleteUser() {
        try {
            final Form<SamRequestForm> ins = formFactory.form(SamRequestForm.class).bindFromRequest();
            if (ins.hasErrors()) {
                return failure(buildValidationErrorMessage(ins.allErrors()));
            }

           *//* if (!ObjectId.isValid(email)) {
                return failure("Invalid User ID type");
            }*//*

            final SamRequestForm newform = ins.get();
            final SamModel user = this.samservice.deleteUser(newform);
            return user != null ? success("successfully deleted user") : failure("failed to delete user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }


           *//* if (!ObjectId.isValid(email)) {
                return failure("Invalid User ID type");
            }*//*
*/
   public Result logoutUser(String sessionToken) {
       System.out.print("Test   " + sessionToken );
       boolean status = this.sessionService.deleteSession(sessionToken);
       return status ? success("You've been successfully logged out") : failure("not logout");
   }

}





