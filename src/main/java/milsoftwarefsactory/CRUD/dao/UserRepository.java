package milsoftwarefsactory.CRUD.dao;

import milsoftwarefsactory.CRUD.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    //what do we want to return (user entity from database
    UserEntity findByEmail (String email);
}
