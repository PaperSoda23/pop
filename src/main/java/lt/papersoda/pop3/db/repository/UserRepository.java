package lt.papersoda.pop3.db.repository;

import lt.papersoda.pop3.db.entity.UserEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<UserEntity> findUserByName(final String userName) {
        try {
            return Optional.of(
                        entityManager
                        .createQuery(
                                "SELECT u from UserEntity u WHERE u.name = :name",
                                UserEntity.class
                        )
                        .setParameter("name", userName)
                        .getSingleResult()
            );
        } catch (EmptyResultDataAccessException | NoResultException exception) {
            return Optional.empty();
        }
    }
}
