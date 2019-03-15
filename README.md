## OAuth2.0 Demo

Demo project for some of the OAuth2.0 authentication and authorization flows.

## MongoDB setup
All applications in this demo must connect to a Mongo database. Preferably, use Docker:
```
docker pull mongo:latest
docker run -d -p 27017:27017
```
The apps use the default Mongo port - __27017__.

## Apps
The project contains 3 SpringBoot apps that serve AngularJS SPAs as follows:
* **oauth2-api**: 
    * considering OAuth terminology, it is both the __resource__ server and the __authentication__ server (e.g. consider Facebook's API)
    * this is where a developer can register an app (trusted-app and client-app) allowing its users to sing-in using their existing account (created with the trusted-app)
    * runs on **8080**
    
* **trusted-app**:
    * considering OAuth terminology, this is a __trusted__ application that is presumably developed by the owners of the resource server (e.g. Facebook's mobile app)
    * register the app with **oauth2_api** and then save the credentials in the _http://localhost:8082/#/credentials_ page
    * we use app this to create an account and sign-in using user/pass (PASSWORD GRANT)
    * If everything goes well, you should see a picture of Morty 😄
    * runs on **8081**

* **client-app**:
    * considering OAuth terminology, this is a __client__ application
    * register the app with **oauth2_api** and then save the credentials in the _http://localhost:8081/#/credentials_ page
    * we don't want the users of this app to create a specific account for it and we would like them to authenticate using the account created via the trusted-app
    * click the "Use Trusted App account" and you will be redirected to an authorization page of trusted-app
    * if everything goes well, you should see a picture of both Rick & Morty 🎉 and the firstname, lastname of your trusted-app account
     * runs on **8082**
    
## Other notes
This is just a demo app, I didn't invest time in dealing with security concerns and so on. Take it for what it is.

## Bonus
![r&m](https://mondrian.mashable.com/uploads%252Fstory%252Fthumbnail%252F78497%252F8a11add4-07e9-4f7b-8164-f63549a7f8db.png%252F950x534.png?signature=7n7FIMltXJkm3HGA6GctlVZIpKc=&source=https%3A%2F%2Fblueprint-api-production.s3.amazonaws.com)
