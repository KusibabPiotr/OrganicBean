package ka.piotr.organicbean.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
