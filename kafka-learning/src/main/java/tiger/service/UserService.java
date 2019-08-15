package tiger.service;

import tiger.module.User;

public interface UserService {
    void sendToKafka(User user);
}
