package milsoftwarefsactory.CRUD.Controller;
import milsoftwarefsactory.CRUD.model.UserAuthenticationEntity;
import milsoftwarefsactory.CRUD.model.UserEntity;
import milsoftwarefsactory.CRUD.dao.UserRepository;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Id;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    //Your @Get, Post, Patch, and Delete Mappings here.
    @GetMapping("/users")
    public Iterable<UserEntity> allById(){
        return this.repository.findAll();
    }
    @GetMapping("/users/{id}")
    public Optional<UserEntity> byId(@PathVariable long id){
        return this.repository.findById(id);
    }
    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity userEntity){
        return this.repository.save(userEntity);
    }
    @PatchMapping("/users/{id}")
    public UserEntity updateUser (@RequestBody UserEntity userEntity,
                                  @PathVariable Long id){
        if (this.repository.existsById(id)){
            UserEntity oldUserEntity = this.repository.findById(id).get();
            oldUserEntity.setEmail(userEntity.getEmail());
            this.repository.save(oldUserEntity);
        }
        else {
            return this.repository.save(userEntity);
        }
        return userEntity;
    }

    //Delete
    @DeleteMapping("/users/{id}")
    public void userDelId(@PathVariable long id){
        this.repository.deleteById(id);

        //return this.repository.deleteById(id);
    }
    @GetMapping("/error")
    public String youBrokeIt(){
        return "What did you do??????";
    }

    //Post mapping to authenticate users to /users/authenticate
    @PostMapping("users/authenticate")
    public UserAuthenticationEntity authenticateUser (@RequestBody UserEntity userEntity){

        //create a new object to compare UserEntities
        //does it exist in UserEntity?
        UserEntity authUserEntity = this.repository.findByEmail(userEntity.getEmail());
        UserAuthenticationEntity userAuthenticationEntity = new UserAuthenticationEntity();
                //if it does then another if statement to check if password matches
        if (authUserEntity.getPassword().equals(userEntity.getPassword())){
            userAuthenticationEntity.setUser(authUserEntity);
            userAuthenticationEntity.setAuthenticated(true);
            //if it does match, then update UserAuthenticationEntity isAuthenticated true
        } else {
            userAuthenticationEntity.setAuthenticated(false);
        }

        return userAuthenticationEntity;
                        //Update User Entity in UserAuthenticationEntity return authenticated=true
                            //if passwords don't match, return authenticated=false


        //return UserAuthenticationEntity
    }

}
