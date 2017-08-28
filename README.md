# lagos-developers
An Andela Android Intermediate Challenge App

This Android app is an that retrieve a list of Java Developers in Lagos using the Github API 
Display the list of developers on a list. Each item on the list should have:
User’s profile image
User’s GitHub username
Clicking each item on the list takes you to their profile details.
The profile screen contains:
Username
Profile photo
Github profile URL
A Button to share their profile, and launch a share intent and the content of the share is “Check out this awesome developer @<github username>, <github profile url>.”
Clicking on the Github url launch the browser and link to their Github page.


This android helps to fetch the repository of developers around the world when their username is entered

SlideView is used in the welcome activity to display the pictures of developers
RecyclerView and CardView are used to display the github data fetched using Volley network calls in the RestFull activities
