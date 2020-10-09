# CodeFellowship

### Routes

"/" : This is the home route. All users have permission to access this route. The page will display only login and signup if the user is not logged in. If the user is logged in, "myprofile" "forum" and logout links will display. As well as, a field to search the forum data activity by "other" users.

"/login" : This route shows fields for user name and password. If the user is authorized, it will redirect to "/" route.

"/signup" : This route shows form fields for user data. Once submited the page will redirect to the "/login" page.

"/forum" : This is the main forum page that has a field for authorized users to post messages to the forum database that will display for all users also in the forum (No TCP connection, users will have to refresh to see latest messages.).

"/myprofile/{username}" : This route redisplays all the user info they signed up with. User will not be able to see other users data except the username they authorized themselves with. It will also show their forum activity.

"/user/{username}" : This route will show the username that was searched for and their forum activity. 

"/feed/{username}" : This routes shows the messages that the people you follow create in the forum. This uses the ManyToMany relational database relationship. 

### Database

This application uses SQL Relational database. The application uses two tables for users, and a relational database "ManyToOne", and "ManyToMany" for message posts that correspond with the user profile stored in the user database. The forum pulls all the entries for display from the message database, and the user/myprofile routes pull from the user database for user specific messages.

### How to use

Be sure to create a database in your local machine using psql and name it "userinfo"
Make sure that the database is "live" before spinning up the app.
If you pull this application to run locally. Be sure to go to application properties to change the JPA to create to wipe out the old database. Then after the app is live, go back to change it to "update" so you avoid losing your saved data.

Then sign up with multiple users and post to the forum! You will be able to see all the posts specific to the route parameters!
