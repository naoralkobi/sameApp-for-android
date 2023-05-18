# SAME App

Welcome to SAME app, a multi-platform messaging application designed for seamless communication. Please note that this project was developed by Naor and Aviv, who encountered technical difficulties due to using different operating systems. As a result, we have two separate repositories, and this readme will guide you through the final result.

## Getting Started

To use our app, follow these simple steps:

1. Clone our repository, which contains two projects: the server and web app (built with .NET), and the Android app.
2. Run the server (using .NET) and wait for the web application to load.
3. Launch the Android app.
4. Register an account within the Android app (currently, registration is only supported on Android devices). Your password must contain 8 characters, including at least 1 alphabetical letter.
5. Log in to the app from either the web app or the Android app and start conversations.

**NOTE:** If you register from the Android app, you have the option to add a profile picture, which will only be displayed on your phone.

**NOTE:** You can only communicate with users who are logged into the same server as you.

To fully experience our app, register at least two users and log in to each of them. You can use different browsers, two phones, or a combination of phone and browser. Once registered, you can add new contacts to your chat and exchange messages.

Currently, the app only supports sending text messages.

When adding a new contact, you need to provide the following information:

- The user's username as registered in the app's database.
- A nickname of your choice for the contact.
- The server on which the contact is logged in.

**NOTE:** If you want to send a message from the web app, click on the chat first and then send the message.

## Important Notes

Please take note of the following important details:

1. When you send a message or add a new contact, you will only see the changes after refreshing the page. We apologize for any inconvenience caused.
2. The default server address is "localhost:7001". To change it to your current server address, go to the settings page and update the server address. When entering your current server, specify only the port. For example, if your server address is "example.com:5324", input "5324".
3. If you add a new contact from the web app, the server address is "localhost:5324". Please modify the digits to match your server address.

Please be aware that each login session lasts for 6000 seconds (approximately 1.5 hours), after which you will need to log in again.

Thank you for choosing SAME app. We hope you enjoy using our platform for seamless communication.
