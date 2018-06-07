package com.demo.domain.chat.interactors;

import com.demo.domain.chat.models.Message;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.executor.impl.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetMessagesUseCase extends UseCase<List<Message>, String>{

    private MessageRepository messageRepository;

    @Inject
    protected GetMessagesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                 MessageRepository messageRepository) {
        super(threadExecutor, postExecutionThread);
        this.messageRepository = messageRepository;
    }

    @Override
    protected Flowable<List<Message>> buildUseCaseObservable(String param) {
        return messageRepository.getMessagesObservable();
    }
}
