package lt.papersoda.pop3.db.repository;

import lt.papersoda.pop3.db.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findUserByName(final String userName);
}
