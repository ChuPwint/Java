import acm.program.ConsoleProgram;

import java.util.LinkedList;

public class NoodleMailIncSim extends ConsoleProgram {

        MailServer noodleServer;
        MailServer jabbooServer;

        public static final String MENU_ITEM_COMPOSE = "compose";
        public static final String SUBMENU_ITEM_CANCEL = "c";
        public static final String SUBMENU_ITEM_SEND = "s";

        public static final String MENU_ITEM_INBOX = "inbox";
        public static final String SUBMENU_ITEM_READ = "r";
        public static final String MENU_ITEM_SENT = "sent";

        public static final String DOMAIN_NOODLE = "noodle.com";
        public static final String DOMAIN_JABBOO = "jabboo.com";

        @Override
        public void run() {

                setFont("JetBrains Mono NL-16");

                noodleServer = new MailServer("noodle.com");
                jabbooServer = new MailServer("jabboo.com");
                noodleServer.connectWith(jabbooServer);
                jabbooServer.connectWith(noodleServer);

                setupInitialState();

                // HOMEWORK >>> Read status
                // HOMEWORK >>> sent
                // HOMEWORK >>> places where errors can occur or need improvement
                processMainMenu();

        }

        private void processMainMenu() {
                println("***** Noodle Mail Sim Program *****");
                println("Enter `" + MENU_ITEM_COMPOSE + "` to compose an email.");
                println("Enter `" + MENU_ITEM_INBOX + "` to check email inbox.");
                println("Enter `" + MENU_ITEM_SENT + "` to check sent emails.");
                while (true) {
                        String choice = readLine("Enter one of "
                                        + MENU_ITEM_COMPOSE
                                        + "/"
                                        + MENU_ITEM_INBOX
                                        + "/"
                                        + MENU_ITEM_SENT
                                        + ": ");
                        if (MENU_ITEM_COMPOSE.equals(choice)) {
                                processComposeMenu();
                        } else if (MENU_ITEM_INBOX.equals(choice)) {
                                processInboxMenu();
                        } else if (MENU_ITEM_SENT.equals(choice)) {
                                // TO DO: HOMEWORK
                        } else {
                                println("No such menu: " + choice);
                        }
                }
        }

        private void processComposeMenu() {
                String toAddress = readLine("Enter recipient email address: ");
                String fromAddress = readLine("Enter sender email address: ");
                String subject = readLine("Enter subject: ");
                String body = readLine("Write your mail: ");
                String choice = readLine("Enter `"
                                + SUBMENU_ITEM_SEND
                                + "` to send or `"
                                + SUBMENU_ITEM_CANCEL
                                + "` to cancel: ");
                if (SUBMENU_ITEM_SEND.equals(choice)) {
                        MailMessage msg = new MailMessage(toAddress, fromAddress, subject, body);
                        // HOMEWORK >>> think about how to make it safer,
                        // what problem you may encounter with current approach
                        // user1@noodle.com  >>> user1noodle.com@jabboo.com
                        if (fromAddress.contains(DOMAIN_NOODLE)) {
                                noodleServer.sendMail(msg);
                        } else if (fromAddress.contains(DOMAIN_JABBOO)) {
                                jabbooServer.sendMail(msg);
                        } else {
                                println("Server not found for " + fromAddress + "!");
                        }
                }
        }

        private void processInboxMenu() {
                String emailAddress = readLine("Enter email address: ");
                LinkedList<MailMessage> msgs = null;
                if (emailAddress.contains(DOMAIN_NOODLE)) {
                        msgs = noodleServer.getInboxFor(emailAddress);
                } else if(emailAddress.contains(DOMAIN_JABBOO)) {
                        msgs = jabbooServer.getInboxFor(emailAddress);
                } else {
                        println("Server not found for " + emailAddress + "!");
                }
                if (msgs != null) {
                        displayMessageList(msgs);
                        processReadMenu(msgs);
                }
        }

        private void displayMessageList(LinkedList<MailMessage> messages) {
                print("No");
                print("\tfrom              ");
                print("\tSubject           ");
                println("\tMessage         ");

                for (int i = 0; i < messages.size(); i++) {
                        MailMessage msg = messages.get(i);
                        print(i);
                        print("\t" + msg.getFromAddress());
                        print("\t" + msg.getSubject());

                        // need improvement...
                        println("\t" + msg.getBody().substring(0, 3) + "...");
                }
        }

        private void processReadMenu(LinkedList<MailMessage> msgs) {
                String choice = readLine("Enter `"
                                + SUBMENU_ITEM_READ
                                + "` to read an email or `"
                                + SUBMENU_ITEM_CANCEL
                                + "` back to main menu: ");
                if (SUBMENU_ITEM_READ.equals(choice)) {
                        int mailSeq = readInt("Enter mail No: ");
                        MailMessage toRead = msgs.get(mailSeq);
                        displayMessageToRead(toRead);

                } else {
                        println("...");
                }
        }

        private void displayMessageToRead(MailMessage msg) {
                println(msg.getFromAddress());
                println("\t" + msg.getSubject());
                println("\t" + msg.getBody());
        }

        private void testSendMailToMailServer() {
                MailServer noodleServer = new MailServer("noodle.com");
                MailServer jabbooServer = new MailServer("jabboo.com");
                noodleServer.connectWith(jabbooServer);
                jabbooServer.connectWith(noodleServer);

                MailMessage msg1 = new MailMessage("user1@noodle.com", "user2@noodle.com", "Subject 1", "Hello world!");
                MailMessage msg2 = new MailMessage("user1@jabboo.com", "user2@noodle.com", "Subject 1", "Hello world!");

                noodleServer.sendMail(msg1);
                noodleServer.sendMail(msg2);
                println(noodleServer.getTotalNoOfMessages());
                println(jabbooServer.getTotalNoOfMessages());
        }

        private void setupInitialState() {
                // send via noodle.com
                MailMessage ndlMsg1 = new MailMessage("user1@noodle.com", "user3@noodle.com", "Subject 1", "Hello world 1!");
                MailMessage ndlMsg2 = new MailMessage("user2@noodle.com", "user3@noodle.com", "Subject 2", "Hello world 2!");
                MailMessage ndlMsg3 = new MailMessage("user1@jabboo.com", "user3@noodle.com", "Subject 3", "Hello world 3!");
                MailMessage ndlMsg4 = new MailMessage("user2@jabboo.com", "user3@noodle.com", "Subject 4", "Hello world 4!");

                MailMessage ndlMsg5 = new MailMessage("user3@noodle.com", "user1@noodle.com", "Subject 5", "Hello world 6!");
                MailMessage ndlMsg6 = new MailMessage("user3@noodle.com", "user2@noodle.com", "Subject 6", "Hello world 7!");
                MailMessage ndlMsg7 = new MailMessage("user3@noodle.com", "user2@noodle.com", "Subject 7", "Hello world 8!");
                MailMessage ndlMsg8 = new MailMessage("user3@noodle.com", "user1@noodle.com", "Subject 8", "Hello world 9!");


                noodleServer.sendMail(ndlMsg1);
                noodleServer.sendMail(ndlMsg2);
                noodleServer.sendMail(ndlMsg3);
                noodleServer.sendMail(ndlMsg4);

                noodleServer.sendMail(ndlMsg5);
                noodleServer.sendMail(ndlMsg6);
                noodleServer.sendMail(ndlMsg7);
                noodleServer.sendMail(ndlMsg8);
        }

        private void testInbox() {
                MailServer noodleServer = new MailServer("noodle.com");
                MailServer jabbooServer = new MailServer("jabboo.com");
                noodleServer.connectWith(jabbooServer);
                jabbooServer.connectWith(noodleServer);

                // send via noodle.com
                MailMessage ndlMsg1 = new MailMessage("user1@noodle.com", "user2@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg2 = new MailMessage("user2@noodle.com", "user2@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg3 = new MailMessage("user1@jabboo.com", "user2@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg4 = new MailMessage("user2@jabboo.com", "user2@noodle.com", "Subject 1", "Hello world!");


                noodleServer.sendMail(ndlMsg1);
                noodleServer.sendMail(ndlMsg2);
                noodleServer.sendMail(ndlMsg3);
                noodleServer.sendMail(ndlMsg4);

                MailMessage msg = noodleServer.getInboxFor("user2@noodle.com").get(0);
                println(msg.getToAddress());
        }

        private void testSentMails() {
                MailServer noodleServer = new MailServer("noodle.com");
                MailServer jabbooServer = new MailServer("jabboo.com");
                noodleServer.connectWith(jabbooServer);
                jabbooServer.connectWith(noodleServer);

                // send via noodle.com
                MailMessage ndlMsg1 = new MailMessage("user1@noodle.com", "user3@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg2 = new MailMessage("user2@noodle.com", "user4@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg3 = new MailMessage("user1@jabboo.com", "user6@noodle.com", "Subject 1", "Hello world!");
                MailMessage ndlMsg4 = new MailMessage("user2@jabboo.com", "user3@noodle.com", "Subject 1", "Hello world!");


                noodleServer.sendMail(ndlMsg1);
                noodleServer.sendMail(ndlMsg2);
                noodleServer.sendMail(ndlMsg3);
                noodleServer.sendMail(ndlMsg4);

                MailMessage msg1 = noodleServer.getOutboxFor("user3@noodle.com").get(0);
                MailMessage msg2 = noodleServer.getOutboxFor("user3@noodle.com").get(1);
                println(msg1.getFromAddress());
                println(msg2.getFromAddress());
        }

}
