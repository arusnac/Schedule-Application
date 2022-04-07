# Schedule-Application
Application that allows scheduling and managing appointments coded in Java with JavaFX.

Using MYSQL it allows the user to login to establish a connection to the database.

From there it pulls data from the database and features the ability to create, update, and delete customers as well as appointments. The application uses the timezone settings of the user for display purposes but adds items to the database with UTC time. There are various error checking mechanisms throughout. From verifying that all required fields are filled out to making sure appointment times do not overlap.

One of the final features the appication has is generating reports such as how many customers are scheduled for each month, type of appointment or location.

**Challenges**
One of the biggest challenges I came across was making sure the datetime objects were being correctly translated.  

**Things I would do differently**
If I were to redo this application or update it, I would improve it's user interface. Not just aesthically but also make it more intuitive and feel more responsive. As it is it feels rather clunky.
