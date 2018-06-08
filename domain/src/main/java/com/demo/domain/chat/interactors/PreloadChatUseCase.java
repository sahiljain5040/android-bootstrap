package com.demo.domain.chat.interactors;

import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.executor.impl.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class PreloadChatUseCase extends UseCase<Boolean, String>{

    private MessageRepository messageRepository;

    @Inject
    protected PreloadChatUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                 MessageRepository messageRepository) {
        super(threadExecutor, postExecutionThread);
        this.messageRepository = messageRepository;
    }

    @Override
    protected Flowable<Boolean> buildUseCaseObservable(String param) {
        try {
            messageRepository.loadMessages();
            return Flowable.just(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Flowable.just(false);
        }
    }
}
