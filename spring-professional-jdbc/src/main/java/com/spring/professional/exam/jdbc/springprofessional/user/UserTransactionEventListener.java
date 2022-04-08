package com.spring.professional.exam.jdbc.springprofessional.user;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserTransactionEventListener {

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleOrderCreatedEvent(UserCreatedEvent creationEvent) {
       System.err.println("");
    }
}
