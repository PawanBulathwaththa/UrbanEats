package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.entity.ContactMessage;
import dm02project.nibm.kahdse242f.urbanfood.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public void saveMessage(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }
}