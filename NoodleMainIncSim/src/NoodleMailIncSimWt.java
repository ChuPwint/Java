import acm.program.ConsoleProgram;

public class NoodleMailIncSimWt extends ConsoleProgram {


        public void run() {
                MailServer noodle = new MailServer("noodle.com");
                MailServer jabboo = new MailServer("jabboo.com");
                noodle.connectWith(jabboo);
                jabboo.connectWith(noodle);

                MailMessage m1 = new MailMessage("user1@noodle.com", "user3@noodle.com", "subject  1", "Hello user1");
                noodle.sendMail(m1);

                MailMessage m2 = new MailMessage("user1@jabboo.com", "user3@noodle.com", "subject  1", "Hello user1");
                noodle.sendMail(m2);

                println(noodle.getTotalNoOfMessages());
                println(jabboo.getTotalNoOfMessages());

        }

        // menu processing methods
}
