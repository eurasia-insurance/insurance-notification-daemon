package tech.lapsa.insurance.notificationDaemon.drivenBeans;

import static tech.lapsa.insurance.shared.jms.InsuranceDestinations.*;

import java.util.Locale;

import javax.ejb.MessageDriven;
import javax.inject.Inject;

import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.RequesterData;
import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.insurance.notificationDaemon.resources.QRecipientUser;
import tech.lapsa.insurance.notificationDaemon.template.NotificationMessages;
import tech.lapsa.insurance.notificationDaemon.template.NotificationTemplates;
import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailFactory;
import tech.lapsa.lapsa.mail.MailMessageBuilder;

@MessageDriven(mappedName = NOTIFIER_NEW_POLICY_USER_EMAIL)
public class NewPolicyUserEmailDrivenBean extends EmailRequestNotificationBase<PolicyRequest> {

    @Inject
    @QRecipientUser
    protected MailFactory mailFactory;

    public NewPolicyUserEmailDrivenBean() {
	super(PolicyRequest.class);
    }

    @Override
    protected MailFactory mailFactory() {
	return mailFactory;
    }

    @Override
    protected MailMessageBuilder recipients(final MailMessageBuilder builder, final Request request)
	    throws MailBuilderException {
	final RequesterData requester = request.getRequester();
	return builder.withTORecipient(requester.getEmail(), requester.getName());
    }

    @Override
    protected Locale locale(final Request request) {
	return request.getRequester().getPreferLanguage().getLocale();
    }

    @Override
    protected NotificationMessages getSubjectTemplate() {
	return NotificationMessages.POLICY_USER_EMAIL_SUBJECT;
    }

    @Override
    protected NotificationTemplates getBodyTemplate() {
	return NotificationTemplates.NEW_POLICY_USER_EMAIL_TEMPLATE;
    }
}
