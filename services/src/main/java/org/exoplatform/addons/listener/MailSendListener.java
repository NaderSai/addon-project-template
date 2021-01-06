package org.exoplatform.addons.listener;

import org.exoplatform.commons.utils.MailUtils;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.branding.BrandingService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserEventListener;
import org.exoplatform.services.organization.UserProfile;
import org.exoplatform.services.organization.impl.NewUserConfig;
import org.exoplatform.services.resources.ResourceBundleService;
import org.exoplatform.web.WebAppController;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class MailSendListener extends UserEventListener {

    private static final Log LOG = ExoLogger.getLogger(MailSendListener.class);


    private final MailService mailService;
    private final ResourceBundleService bundleService;
    private final BrandingService brandingService;
    private NewUserConfig config_;

    public MailSendListener(MailService mailService, ResourceBundleService bundleService, BrandingService brandingService, WebAppController webController) {
        super();
        this.mailService = mailService;
        this.bundleService = bundleService;
        this.brandingService = brandingService;
    }

    public boolean SendMail(User user, Locale locale) {
        if (user == null) {
            throw new IllegalArgumentException("User or Locale must not be null");
        }

        ResourceBundle bundle = bundleService.getResourceBundle(bundleService.getSharedResourceBundleNames(), locale);
        String emailBody = "";
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("conf/onBoarding_email_template.html");
        emailBody = emailBody.replaceAll("\\$\\{USER_DISPLAY_NAME\\}", user.getDisplayName());
        emailBody = emailBody.replaceAll("\\$\\{COMPANY_NAME\\}", brandingService.getCompanyName());
        emailBody = emailBody.replaceAll("\\$\\{CHANGING_PASSWORD_LINK\\}", "http://127.0.0.1:8080/portal/forgot-password");
        String emailSubject = bundle.getString("onboarding.email.header") + " " + brandingService.getCompanyName();
        String senderName = MailUtils.getSenderName();
        String from = MailUtils.getSenderEmail();
        if (senderName != null && !senderName.trim().isEmpty()) {
            from = senderName + " <" + from + ">";
        }

        Message message = new Message();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject(emailSubject);
        message.setBody(emailBody);
        message.setMimeType("text/html");

        try {
            mailService.sendMessage(message);
        } catch (Exception ex) {
            LOG.error("Failure to send onboarding email", ex);
            return false;
        }
        return true;

    }


    public void preSave(User user, boolean isNew) throws Exception {
        LOG.info("Before user is added into database");
    }


    public void postSave(User user, boolean isNew) throws Exception {
        ExoContainer pcontainer = ExoContainerContext.getCurrentContainer();
        OrganizationService service = (OrganizationService) pcontainer.getComponentInstanceOfType(OrganizationService.class);
        UserProfile up = service.getUserProfileHandler().createUserProfileInstance();
        up.setUserName(user.getUserName());
        service.getUserProfileHandler().saveUserProfile(up, false);

        Locale locale = new Locale("en", "US");

        LOG.info("After user is added into database");
        SendMail(user, locale);

    }

}