# Overview
This is starter template for writing Android apps using **Clean architecture**. You can download it, modify it and start building your apps on top of it. Most of the boilerplate code for writing your first view, presenter interactor( UseCase ) and repository is already written and you just need to implement your own logic.

In this template I have used **Rxjava** along with  regular Java and **Dagger** for Dependency Injection. Although the architecture itself is probably complex, but in today's World any modern app is incomplete without these two  **Jargon's**

## Libraries included

 - [RxJava] for reactive programming.
 - [Butterknife] for view injection.
 - [Retrofit] for network code.
 - [JUnit] and [Mockito] for testing.
 - [Android Support Library] for backwards compatibility.

## Clean Architecture

![Alt text](https://res.cloudinary.com/practicaldev/image/fetch/s--c0f9PFvt--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/http://wahibhaq.github.io/img/blog/posts/summary-thoughts-clean-architecture-mvp/clean-architecture-ring-diagram.png "Optional title")

> In Clean Architecture, the code is separated into layers in an onion shape with one dependency rule: The inner layers should not know anything about the outer layers. Inner layers contain business logic, whereas the outer layers contain implementation and the middle layer contain Interface Adapters. Each ring represent one layer of abstraction

## Different layers, components and how they communicate with each other

![Alt text](https://res.cloudinary.com/practicaldev/image/fetch/s--ahOh6vkO--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/http://wahibhaq.github.io/img/blog/posts/summary-thoughts-clean-architecture-mvp/srp-clean-architecture-diagram.png )

## Structure
The general structure for the android app looks like this:

 - Outer Layer: UI, Storage, Network etc.
 - Middle Layer: Presenters, Converters.
 - Inner Layer: Interactors, Models, Repositories, Executor.

## How this fits into our Bootstrap-App

 - **"app"** Module: This includes both the outer layer as well as middle         layer.This is  also called as Presentation Layer. This includes Activities, Fragments, Adapters and other Android code related to the user interface.

- **"data"** module:  This is also outer layer. This module provides the implementation of repositories required by the inner layer.

- **domain** module: This is the inner layer. This module consists of our core business logic. All The UseCase and repositories definities goes here. This module should only consists of POJO classes.

License
----

MIT