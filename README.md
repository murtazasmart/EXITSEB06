# EXITSEB06
Software Engineering Project

The main aim of this project is to develop a multiply poker game with a Graphical User Interface which can support all the poker players, around the world. They can play the game from any location using multiple platforms ends with a good user experience. 

## Things to keep in mind:
1. Use Intellij IDE to run this project.
2. Build the project to obtain the out and jars. Due to space constraints they haven't been added.
3. There are two parts in this project the code which runs on the Server and on the Client
 1. Entry points for Client is Client.GUIThread class.
 2. Entry point for Server is Server.MainServer class.
 
## Folder structure explained
* src
  * Client - Entry point for the client GUI
  * Controller - Files used by the Server to control the games characteristics
  * GUI
    * Services - Contains services which have to be run in the background (and not in the controller) so as to not hang the UI. e.g. socket connections e.t.c.
    * View - Contains the JavaFX fxml files (UI related)
    * Controller - The java classes which control the UI
  * Model - Used by server and clients
  * Resources - Self explantory
  * Server - Entry point for the server
  * Utilities - Self explantory

## Description
 
Foker is a multi-player version of poker which is developed by Team EXIT as 3rd year software engineering project. This game is developed based on java language using

  * Java socket programming to implement multi-player, multi-gaming feature
  * JavaFX to design graphical user interface for better user experience
  * MVC and Object oriented programing concepts to develop well-planned and well-designed software product.
  
In our game players can play the game using any platform which supports java. He/she can connect from any location if he/she has an internet connection. Once a player hosts the game other players can join the game by simply typing the connecting to the game name. No IP addresses have to be used or remembered, unless you are connecting to a local server. The referee of the game is in the server. The referee gets the decisions according to our algorithms which is stored inside the Game object residing in the Controller package. Logic implemented in the game is as follows:
  * Card shuffling
  * Card distribution
  * Exchanging cards
  * Game scoring
  * Risk calculation
  * Winner selection
  
We designed our own algorithms according to make our game unique. We have used queue data structure to keep the cards in card pack, this ensures the card is taken from the top of the pack. Since each player is represented using the player object which is stored inside the game object as an array of players, no particular player can obtain special treatment. Each player/clients socket details, input and output streams are stored using the Client Thread class. By using javaFX along with material design UI our applications provides the best user experience. We’ve used a simple design graphical user interface this ensures minimal confusion. We have used hints, alerts and various other tools to ensure that the client/player isn’t lost while playing the game or while the server is waiting for other players to respond.

## Game Requirements.
 For this project we have satisfied the following 2 key requirements
  * Need at least 2 players to start the multiplayer game.
  * We changed the rules and marking procedure of the traditional poker game so players find the game refreshing, new and interesting.    To find out the new rules players can read the help document.
  
## Additional features.
   Multiple Game Hosting Facility.
  * Local Server and Global Server Hosting Option
  * Background Music
  * Registration free Playing Facility.(complex registration, passwords not required)
  
## Future enhancement
  * Group Chat
  * Tournaments
  * Users allow to add notes about game while playing. 
