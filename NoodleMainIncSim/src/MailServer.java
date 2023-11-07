import java.util.LinkedList;

public class MailServer {

        private String domain;
        private MailServer other; // the other mail server to which this mail server connected to
        private LinkedList<MailMessage> mailMessages;

        public MailServer(String domain) {
                this.domain = domain;
                mailMessages = new LinkedList<MailMessage>();
                this.other = other;
        }

        public void sendMail(MailMessage mail) {
                mailMessages.add(mail);

                // forward mail to other server if recipient is on other domain
                if (!mail.getToAddress().contains(domain)) {
                        other.sendMail(mail);
                }
        }

        // Homework >>> try with constructor
        public void connectWith(MailServer other) {
                this.other = other;
        }

        public int getTotalNoOfMessages() {
                return mailMessages.size();
        }

        public LinkedList<MailMessage> getInboxFor(String mailAddress) {
                LinkedList<MailMessage> inbox = new LinkedList<MailMessage>();
                for (int i = 0; i < mailMessages.size(); i++) {
                        MailMessage msg = mailMessages.get(i);
                        if (msg.getToAddress().equals(mailAddress))
                                inbox.add(msg);
                }
                return inbox;
        }

        public LinkedList<MailMessage> getOutboxFor(String mailAddress) {
                LinkedList<MailMessage> sentMsgs = new LinkedList<MailMessage>();
                for (int i = 0; i < mailMessages.size(); i++) {
                        MailMessage msg = mailMessages.get(i);
                        if (msg.getFromAddress().equals(mailAddress))
                                sentMsgs.add(msg);
                }
                return sentMsgs;
        }
}
