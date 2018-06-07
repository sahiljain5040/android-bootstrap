package com.demo.domain.chat.interactors;

import com.demo.domain.chat.models.Message;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.executor.impl.ThreadExecutor;

import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class SendMessageUseCase extends UseCase<Boolean, String>{

    private MessageRepository messageRepository;

    @Inject
    protected SendMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                 MessageRepository messageRepository) {
        super(threadExecutor, postExecutionThread);
        this.messageRepository = messageRepository;
    }

    @Override
    protected Flowable<Boolean> buildUseCaseObservable(String message) {
        Message messageToBe = new Message(UUID.randomUUID().toString(), "OUTGOING", message, "1");
        messageRepository.insertMessage(messageToBe);
        return Flowable.just(true);
    }
}
