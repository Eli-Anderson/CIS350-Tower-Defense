# CIS350-Tower-Defense

Working Document: https://docs.google.com/document/d/1IJov2GVJCX3VaB_XxGHt3o6Q6bUhUzRZz1HasKTR85I/edit

## PURPOSE
To fulfill CIS350's semester-long group project requirement we will
envision, develop, and produce a 2D 'tower defense' game built with 
Java.

## SCOPE
As with any game, the difficulty and time required to make it
depend entirely on the scope. With this in mind we will outline the
basic and stretch goals for the project, but in general we want to
adhere to the simple style of the classic Flash tower defense games that
dominated the web years ago. These games were not technological marvels,
but they were still very enjoyable.

## INSPIRATION
[Bloons TD 5](https://ninjakiwi.com/Games/Tower-Defense/Play/Bloons-Tower-Defense-5.html)




## GOALS

#### BASIC
  * ###### One difficulty setting
  
    Normal base health, monster health/armor/damage/etc., tower damage/
    fire rate/etc., wave speed and number of enemies. Normal reward(?)
  * ###### Three (3) types of towers
  * ###### Three (3) types of enemies
  * ###### Fixed map
  
    Fixed size, pre-determined path
  * ###### Basic enemy AI  
  
    Enemies will follow a pre-determined path.  
    Enemy sprites will change directions based on their movement.
  * ###### Basic tower AI
  
    Towers will shoot enemy closest to the base
  * ###### Base with set amount of health  
  
    Base will lose health when an enemy follows the path and crosses the
    finish  
    Once the base loses all health the game is over
  
#### STRETCH
  * ###### Multiple difficulty settings (easy, medium, hard)
  
    This can affect the base's health, the starting currency, the health 
    and speed of enemies, the cost of towers and upgrades.
  * ###### Several types of towers
  
    Towers will be upgradeable. Towers will have specific types of damage
    and special effects (slow, explosive, fire, etc.)
  * ###### Several types of enemies
  
    Enemies will have defenses and weaknesses to different types of damage
  * ###### Dynamic map creation
  
    Dynamic size, randomly generated paths as well as hand-crafted levels  
  * ###### Advanced(ish) enemy AI
  
    Enemies will follow the path (which may be randomly generated)
    instead of a pre-determined set of directions.
  * ###### Customizable tower AI
  
    Towers will be able to set to target either the Strongest, Weakest,
    First (closest to base), or Last (farthest from base) enemy. 
    
## BEHAVIOURS
#### Game
  * The main engine of the game
  * Creates the map, all GUI components
  * Handles inputs and outputs
  * Runs the game loop
  
#### Map
  * Holds the tiles that make up the map (background and path)
  * Holds the towers placed on the map
  * Creates the path
  
#### Tower
  * Can be placed (either drag and drop or click to place)
  * Placing (a.k.a. building) costs currency based upon the level of tower
  * Once placed, towers can only be sold (and not moved)
  * Towers will shoot at the enemy closest to the base by default
  * STRETCH GOAL: towers can be modified to target different enemies
  * STRETCH GOAL: towers can be upgraded to better version
    (i.e. more damage or better fire rate) after they are built, which costs
    a set amount of currency based on the current level up upgrade. This can
    only be done a set number of times (3 upgrades per tower, for example)
#### Enemy
  * Is created when the wave (round) starts
  * Moves along the path towards the base
  * Once it reaches the base, it damages it according to its damage value
  * Takes damage (health is decreased) from towers, if no health is left
    the enemy is destroyed and a bounty is gifted
  * STRETCH GOAL: enemies have defense/weakness to different kinds of
    tower damage
  