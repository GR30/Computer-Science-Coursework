## Program Name: Wasihngton_Wiggins_Assignment7.py
## Program Desc: Hangman
## Author(s): Gerald A Washington II, Nathaniel D Wiggins
## Date: November 24, 2014

## Pictures to correspond with the number of wrong guesses.
hangmanpics = ['''

                +---+
                |   |
                    |
                    |
                    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
                    |
                    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
               /|\  |
                    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
               /|\  |
               /    |
                    |
                    |
              =========== ''','''
                +---+
                |   |
                O   |
               /|\  |
               / \  |
                    |
                    |
              =========== ''']
output = []         # A list with the same length and characters as the secret word/phrase.
right_guesses = []  # A list that holds every correct guess made.
wrong_guesses = []  # A list that holds every wrong guess made.
new_guess = ""      # Variable to be used for the user's input
display = ""        # Variable to be manipulated to show dashes, spaces, and/or correct letters already guessed.
done = False        # Boolean variable used to show whether the uesr quits the game or not.


# The secret word/phrase to be guessed out.
word = input("Enter any word or phrase: ").upper() 


# This function changes the value of the string variable "display".
def change_display():
    dis = "" # Changes "display"'s new value to a blank string before making new changes.
    for i in range(0,len(word)):
        if word[i].isalpha():
            if word[i] == new_guess:
                dis = dis + output[i] # Adds matching letters to "dis"
            elif word[i] in right_guesses:
                dis = dis + word[i] # Adds already matched letters to "dis"
            else:
                dis = dis + "-" # Adds dashes in place of unmatched letters.
        else:
            dis = dis + word[i] # Adds spaces, dashes, astricks, etc. that are within the secret word/phrase to the display.
    return dis.upper() # "display"'s new value.


# This function checks every guess to make sure it is alphabetical,
# then it checks whether the letter guessed is within the secret word/phrase.
def guess_check(new_guess):
        if len(new_guess) == 1:
             if new_guess.isalpha():
                if (new_guess in right_guesses) or (new_guess in wrong_guesses): # Rules out already guessed letters
                    print("You've already guessed that letter.")
                else:
                    if new_guess in word:
                        right_guesses.append(new_guess) # Stores correctly guessed letters
                    else:
                        wrong_guesses.append(new_guess) # Stores incorrectly guessed letters
                        print("Sorry, wrong guess.")
             else:
                print("Only enter letters.")
        else:
            print("Only enter one letter.")

# This adde every letter/character to the list "output" to be referenced to later.
for i in range(0,len(word)):
    output.append(word[i])

# Clears the screen after the secret word has been input.
for i in range(100):
    print("")
    
# Displays the initial dashes, spaces, and other non-letter characters.
for i in range(0, len(word)):
    if word[i] == " ":
        display = display + " "
    else:
        display = display + "-"
print(display)

# This loop continues until the player quits or guesses every letter of the word/phrase correctly.
while display != word and done == False:
    if len(wrong_guesses) < 6:    
        new_guess = input("Guess any letter (or type \"quit\" to exit): ").upper() # Guess input
        if new_guess == "QUIT":
            done = True # Breaks the loop.
        else:
            guess_check(new_guess) # Checks guess.
            display = change_display() # Changes the letters and other characters shown.
            print("\n" + display) # Prints current display.
            print("Wrong Guesses:",wrong_guesses) # Prints current wrong guesses.
            print(hangmanpics[len(wrong_guesses)]) # Shows and changes the picture for the hangman based on how many wrong guesses the user has made.

    else:
        # Pity message and loop break.
        print (word.upper())
        print ("Good try.\n")
        done = True
        
if display == word:
    print ("Congratulations! You won!\n") # Congratulatory message.
print ("Thanks for playing!") # Final thanks.
