## Build tools & versions used
- Android Studio Chipmunk | 2021.2.1
- Gradle Plugin version: 7.1.3
- Gradle Version: 7.3.3
- compileSdk 32

## Steps to run the app
- Open Android Studio Chipmunk
- Open the Project: File -> Open...
- Wait until after completion of project load and gradle sync
- Make sure you have one virtual device ready for use. If not, create one from Device Manager.
- Run the App: Run -> Run 'App'

## What areas of the app did you focus on?
The focus of the project was put on the architecture side. The goal is make sure a good separation of concern of each layer and support for testing is instilled in the design.
I want you get the most from what you see by looking at the structures. If not, the following description of each folder might help:
- config: contains the configuration of the app. (base url, endpoints)
- data: data layer of the app. The interface is exposed through repository
- di: dependency injection layer which is supported by hilt.
- domain: domain layer, in this case, we have 2 use case to provide data to upper layer. (Sorted Employee List and Get employees from random endpoints with weight)
- model: contains data models.
- ui: contains the ui views, screens and viewmodels, basically ui views/screens are what users see and viewmodels contain and expose ui states which define what users should see.
  - common: contains list item view which is potentially reusable
  - home: contains home screen and building blocks
- test: contains unit tests

## What was the reason for your focus? What problems were you trying to solve?
More focus was put on architecture was basically due to the following reason:
- Focus on the most foundation part in a limited time frame.
- Architecture is my strong suit compared to making beautiful and friendly UIs. (which are important as well)

## How long did you spend on this project?
- A bit more than 6 hours

## Did you make any trade-offs for this project? What would you have done differently with more time?
The trade off was made to spend more time on architecture side as mentioned above. With more time, the following aspects can be done better:
- Ui layer considerations. Currently, no much thought is put on this aspect with regard to how users access the UI, making material UI which boosts user engagement etc.

## What do you think is the weakest part of your project?
With the foundation in place, I am overall satisfied with each part of the project. One obviously improvement is the UI and theming which I need some UX input.

## Did you copy any code or dependencies? Please make sure to attribute them here!
No.

## Is there any other information you’d like us to know?
- The Ui views are made by Jetpack Compose.
- Pull to refresh is supported. Currently, we will loading employee list from 3 of the endpoints randomly based on their wights. Default wight configuration is 3:1:1
- If you encounter any error page, please pull to refresh.