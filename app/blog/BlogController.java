package blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import global.common.BaseController;
import global.exceptions.CustomException;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public final class BlogController extends BaseController /**basecontroller--nothing but the controller class to the mvc models */
{

    private final BlogService blogservice;

    @Inject
    private BlogController(BlogService blogservice) {
        this.blogservice = blogservice;
    }


    @BodyParser.Of(BodyParser.Json.class)

    public Result postBlog() {
        if (isSessionValid()) {
            try {
                final Form<BlogRequestForm> blogModelForm = formFactory.form(BlogRequestForm.class).bindFromRequest();
                if (blogModelForm.hasErrors()) {
                    return failure(buildValidationErrorMessage(blogModelForm.allErrors()));
                }
                final BlogRequestForm blogForm = blogModelForm.get();
                final BlogModel user = this.blogservice.blogpost(blogForm.getUserid());
                if (user != null) {

                    final BlogModel userblog = this.blogservice.postblog(blogForm);
                    return user != null ? success("successfully posted your blog " + blogForm.getUserid()) : failure("blog has not been posted");
                } else {
                    return failure("you are not our existing user..Kindly signup and then post your blog");
                }


            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else {
              return failure("the session expires");

        }

    }

    public Result updateBlog() {
        if(isSessionValid())
        {
        try {
            final Form<BlogRequestForm> blogmodelform = formFactory.form(BlogRequestForm.class).bindFromRequest();
            final BlogRequestForm newform = blogmodelform.get();
            final BlogModel user = this.blogservice.updateblog(newform);
            return user != null ? success("successfully updated user") : failure("failed to update user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }}
        else
        {
            return failure("the session expires");
        }
    }


    public Result deleteBlog() {
        if(isSessionValid())
        {
        try {
            final Form<BlogRequestForm> blogmodel = formFactory.form(BlogRequestForm.class).bindFromRequest();



        final BlogRequestForm newform = blogmodel.get();
        final BlogModel user = this.blogservice.deleteblog(newform);
        return user != null ? success("successfully deleted user") : failure("failed to delete user");
    } catch (CustomException e) {
    return failure(e.getMessage());
}}
else
        {
            return failure("the session expires");
        }
}
    public Result getLike(String email, String blogname) {
        if(isSessionValid())
        {
        try {
            BlogModel user = blogservice.getlike(email, blogname);
            return user != null ? success("liked") : failure("not liked");

        } catch (CustomException e) {
            return failure(e.getMessage());
        }}
        else
        {
            return failure("the session expires");
        }
    }


    public Result maximumLikes()

    {
      if(isSessionValid())
      {
        try {

            List<BlogModel> user = this.blogservice.maxlikes();
            return success("success", blogservice.maxlikes());
        } catch (CustomException e) {
            return failure(e.getMessage());
        }}
        else
      {
          return failure("the session expires");
      }
    }






    public Result postComments() {
        if(isSessionValid())
        {
        try {

            final Form<BlogRequestForm> userForm = formFactory.form(BlogRequestForm.class).bindFromRequest();

            final BlogRequestForm useForm = userForm.get();
            final BlogModel user = this.blogservice.postcomments(useForm);

            return user!= null ? success("posted") : failure("not posted");

        } catch (CustomException e) {
            return failure(e.getMessage());
        }}
        else
        {
            return failure("the session expires");
        }

    }
    public Result latestPosted() {
        try {
            List<BlogModel> user = blogservice.latestposted();
            return success("success",blogservice.latestposted());

        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }



    public Result viewCommentsByUserIds(String userIds) {

        try {
            List<BlogModel> comments = blogservice.viewCommentsByUserIds(userIds);
            System.out.print("Testing   ");
            ObjectMapper mapper1 = new ObjectMapper();
            JsonNode childNode1;
            ArrayNode obj = mapper1.createArrayNode();
            for (BlogModel b : comments) {
                childNode1 = mapper1.createObjectNode();
                if (b.getComment() != null) {
                    ((ObjectNode) childNode1).put("userid", b.getUserid());
                    ((ObjectNode) childNode1).put("comment", b.getComment());

                    ((ArrayNode)obj).add(childNode1);
                }
            }
            return comments != null ? success(obj) : failure("Failed to Fetch Comments");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }

    }


}
