package controllers;

import play.*;
import play.mvc.*;
import views.*;

public class Application extends Controller {

   public Result index(String name,String lastname)
   {
       return ok("Hi" +name+ " " +lastname);
   }
   public Result sam()
   {
       return ok("welcome");
   }

}