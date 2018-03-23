# Overview
This is starter template for writing Android apps using **Clean architecture**. You can download it, modify it and start building your apps on top of it. Most of the boilerplate code for writing your first view, presenter interactor( UseCase ) and repository is already written and you just need to implement your own logic.

In this template I have used **Rxjava** along with  regular Java and **Dagger** for Dependency Injection. Although the architecture itself is probably complex, but in today's World any modern app is incomplete without these two  **Jargon's**.

## Libraries included

 - [RxJava] for reactive programming.
 - [Butterknife] for view injection.
 - [Retrofit] for network code.
 - [JUnit] and [Mockito] for testing.
 - [Android Support Library] for backwards compatibility.

## Clean Architecture

![Alt text](https://res.cloudinary.com/practicaldev/image/fetch/s--c0f9PFvt--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/http://wahibhaq.github.io/img/blog/posts/summary-thoughts-clean-architecture-mvp/clean-architecture-ring-diagram.png)

> In Clean Architecture, the code is separated into layers in an onion shape with one dependency rule: The inner layers should not know anything about the outer layers. Inner layers contain business logic, whereas the outer layers contain implementation and the middle layer contain Interface Adapters. Each ring represent one layer of abstraction

## Different layers, components and how they communicate with each other

![Alt text](https://res.cloudinary.com/practicaldev/image/fetch/s--ahOh6vkO--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/http://wahibhaq.github.io/img/blog/posts/summary-thoughts-clean-architecture-mvp/srp-clean-architecture-diagram.png)

## Structure
The general structure for the android app looks like this:

 - Outer Layer: UI, Storage, Network etc.
 - Middle Layer: Presenters, Converters.
 - Inner Layer: Interactors, Models, Repositories, Executor.

## How this fits into our Bootstrap-App

 - **"app"** module: This includes both the outer layer as well as middle         layer.This is  also called as Presentation Layer. This includes Activities, Fragments, Adapters, Presenters and other Android code related to the user interface.

- **"data"** module:  This is also outer layer. This module provides the implementation of repositories required by the inner layer.

- **"domain"** module: This is the inner layer. This module consists of our core business logic. All The UseCase and repositories(Signatures) goes here. This module should only consists of POJO classes.

## Example
Suppose we need to build a Login Activity, where user need to enter username and password and authenticates over our backend server.

## Getting started writing a new use case (inner/core layer)
```java
public class LoginUseCase extends UseCase<User, Map<String, String>>{

    private LoginRepository mRepository;

    @Inject
    public LoginUseCase(Executor threadExecutor, PostExecutionThread postExecutionThread,LoginRepository respository) {
        super(threadExecutor, postExecutionThread);
        this.mRepository = respository;
    }


    @Override
    public Observable<User> buildUseCaseObservable(Map<String, String> loginParams) {
        return mRepository.loginUser(loginParams);
    }
}
```
## Writing LoginRepository Interface (inner/core layer)

```java
public interface LoginRepository {
    Observable<User> loginUser(Map<String, String> loginParams);
}
```

## Providing LoginRepository Implementation (data layer)
```java
public class LoginApiRepository implements LoginRepository {

    private LoginApi mLoginApi;

    @Inject
    public LoginApiRepository(Retrofit retrofit){
        mLoginApi = retrofit.create(LoginApi.class);
    }

    @Override
    public Observable<User> loginUser(Map<String, String> loginParams) {
        return mLoginApi.loginUser(loginParams);
    }
}
```

## Writing Presenter for LoginActivity (app layer)
```java
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter{

    private LoginUseCase mLoginUseCase;
    private LoginPresenter.View mView;

    @Inject
    public LoginPresenterImpl(LoginUseCase LoginUseCase) {
        super();
        this.mLoginUseCase = LoginUseCase;
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }

    @Override
    public void login() throws Exception {

        if(mView != null){
            mView.onLoginStarted();
        }else{
            throw new Exception("setView() not called Before calling login()");
        }

        mLoginUseCase.dispose();
        mLoginUseCase.execute(getLoginObserver(),getLoginParams());
    }

    private DisposableObserver<User> getLoginObserver(){

        return new DisposableObserver<User>(){
            @Override
            public void onNext(User user) {
                super.onNext(searchResults);
                mView.onLoginSuccess(user);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                mView.onLoginFailed();
            }
        };
    }

    @Override
    public void destroy() {
        mView = null;
        mLoginUseCase.dispose();
    }

    private Map<String, String> getLoginParams() {
        HashMap<String, String> loginParams = new HashMap<String, String>();
        loginParams.put("user_name", mView.getUserName());
        loginParams.put("user_name", mView.getPassword());
        return loginParams;
    }
```

License
----

MIT