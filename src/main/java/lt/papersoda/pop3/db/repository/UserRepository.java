package lt.papersoda.pop3.db.repository;

import lt.papersoda.pop3.db.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{}
