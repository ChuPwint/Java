public class MailMessage {

        private String fromAddress;
        private String toAddress;
        private String subject;
        private String body;

        public MailMessage(String toAddress, String fromAddress, String subject, String body) {
                this.toAddress = toAddress;
                this.fromAddress = fromAddress;
                this.subject = subject;
                this.body = body;
        }

        public String getFromAddress() {
                return fromAddress;
        }

        public String getToAddress() {
                return toAddress;
        }

        public String getSubject() {
                return subject;
        }

        public String getBody() {
                return body;
        }

        public boolean isSentBy(String fromAddress) {
                return this.fromAddress.equals(fromAddress);
        }

        public boolean isSentTo(String toAddress) {
                return this.toAddress.equals(toAddress);
        }
}
