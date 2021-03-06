package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.db.entity.MailEntity;
import lt.papersoda.pop3.db.repository.IMailRepository;
import lt.papersoda.pop3.pojo.ErrorResponse;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.pojo.SuccessResponse;
import lt.papersoda.pop3.user.UserConnectionState;
import lt.papersoda.pop3.user.UserSessionState;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetrCommand implements ICommand {
    private final IMailRepository mailRepository;

    public RetrCommand(IMailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Override
    public Response apply(List<String> arguments, UserSessionState userSessionState) {
        if (userSessionState.getUserConnectionState() != UserConnectionState.TRANSACTION)
            return new ErrorResponse("authentication required before using RETR");

        if (argumentsNotValid(arguments))
            return new ErrorResponse("RETR command expects positive integer as argument");

        Integer mailId;
        try { mailId = NumberUtils.createInteger(arguments.get(0)); }
        catch (NumberFormatException ex) { return new ErrorResponse("provide positive integer as argument"); }

        if (mailId < 0)
            return new ErrorResponse("positive mail id expected");

        final Optional<MailEntity> mailEntityOptional = mailRepository.findById(mailId.longValue());

        if (mailEntityOptional.isEmpty())
            return new ErrorResponse("no mail with id provided");

        return new SuccessResponse(mailEntityOptional.get().toString());
    }

    private boolean argumentsNotValid(List<String> arguments) {
        return arguments.size() != 1;
    }
}
