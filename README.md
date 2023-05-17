# GameJava

# Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Execution](#execution)

## REQUIREMENTS

- JavaFX (Version 20.0.1)

## EXECUTION

Open a terminal, move to the root of the project folder, where is located the file `GameJava_MatheoPEREIRA_MelvynMUNOZ.jar`.

```
cd <your_path_to_jar_file>
```

Execute the jar file (WARNING : the location of "C:\Program Files\Java\javafx-sdk-20.0.1\lib" is an example, you have to replace this by the location of your 'lib' folder in your javafx-sdk folder):

```
java --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.media,javafx.fxml -jar GameJava_MatheoPEREIRA_MelvynMUNOZ.jar
```

## INITIALISATION

Click once on the "Init" button to initialize the map with the player 

## STARTING THE GAME

Click once on the "Start" button to apply gravity and activate the character's and enemy's movements.

Click on the button "Help" to know the know the keys to use to move the character and move the inventory choice.

## GOAL OF THE GAME

You have to collect all the apples and, after that, reach the flag.

## END OF THE GAME

You lose if your character is killed by the spikes or the monster.

## RESTART

You have to close the window and run the jar again.

## OTHER INDICATIONS 

To get out of the hole where the pnj is, you have to talk to him with the "E" key. He can give you :

- a trampoline(single use) if you did not get all the apples accessible at the beginning of the map, it will serve you to jump once higher- to get out of the hole

- a feather (infinite use) that will allow you to jump higher to access all the other apples (to get out of the hole with the feather you have to stand on the right edge and jump going to the left)

To use the items in the inventory, move the red square with the left or right arrows to the desired item.

To kill the monster you have to jump on its head. If you hit it from the side, your character will die.

To see how many apples you have collected, there is a counter called "Apples". For the victory you need to have this counter at 10.