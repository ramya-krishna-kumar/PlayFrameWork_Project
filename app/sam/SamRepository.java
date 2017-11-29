package sam;

import org.bson.types.ObjectId;

import java.util.List;

public interface SamRepository {




   SamModel createUser(SamModel newUser);
   SamModel getUserByUserid(final String name);
   SamModel getsignin(String userid,String password);


   SamModel getUser(String email);
  /* SamModel getLikefield();
   void deleteUser(final SamModel user);*/

}
