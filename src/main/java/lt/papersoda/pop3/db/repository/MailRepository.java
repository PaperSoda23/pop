package lt.papersoda.pop3.db.repository;

import lt.papersoda.pop3.db.entity.MailEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailRepository extends CrudRepository<MailEntity, Long> {}
