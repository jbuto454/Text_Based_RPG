# Text_Based_RPG

Welcome to the final battle against the enemy’s forces! Your mission is to defeat the
computer’s units before your units are knocked out. This is a strategic turn-based game where
two players, one human and one computer, compete by controlling units with specific jobs and
abilities. The goal is to defeat the opponent's units by reducing their Health Points (HP) to zero
through a series of tactical moves and attacks.

At the start of the game, both the human and computer players are assigned three units.
Each unit is randomly given a job, such as Knight, Mage or Archer, and a level, which
determines its attributes like HP, Attack, Evasion, and Defense. These attributes play a
crucial role in the units' performance during battles.

The game begins with the human player's turn. During their turn, the human player controls
each of their units one by one. For each unit, the player is presented with its job type and
level, indicating its readiness to act. The player can choose from two actions for each unit:
attack or block. If “attack” is selected, the unit targets an opposing unit and deals damage
based on the attacking unit's Attack stat value and the defending unit's Defense stat value.
Before the damage is applied, the defending unit may attempt to evade the attack. If the
defending unit's Evasion stat is high enough, the attack may be dodged entirely, resulting in
no damage. If the evasion attempt is unsuccessful, the damage is then calculated by
factoring in the defending unit's Defense stat and any temporary defense adjustments. If
“block” is selected, the unit receives a temporary increase in the unit's Defense stat value to
reduce incoming damage from the opponent's attacks.

Once the human player has completed actions for all of their three units, the game proceeds
to the computer player's turn. The computer player automatically controls its units,
performing similar actions to those of the human player. Each unit acts in sequence, with the
computer deciding the best moves to defeat the human player's units.

The game is turn-based, with each player taking turns until a winner is determined or the
turn limit (10 turns) is reached. Each player's turn involves taking actions with all their living
units. The game continually checks for a winner at the end of each player’s turn. The game
ends when a player defeats all of the opponent's units. If the game reaches 10 turns without
a winner, the player with the higher combined HP of their units wins.
