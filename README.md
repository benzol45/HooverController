# Hoover controller  

## Task
The application should navigate an imaginary robotic hoover (much like a Roomba) through an equally imaginary room based on: 
* room dimensions as X and Y coordinates, identifying the top right corner of the room rectangle. This room is divided up in a grid based on these dimensions; a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions. The bottom left corner is the point of origin for our coordinate system, so as the room contains all coordinates its bottom left corner is defined by X: 0 and Y: 0.
* locations of patches of dirt, also defined by X and Y coordinates identifying the bottom left corner of those grid positions.
* an initial hoover position (X and Y coordinates like patches of dirt)
* driving instructions (as cardinal directions where e.g. N and E mean "go north" and "go east" respectively)
  
The room is a rectangular, has no obstacles (except the room walls), no doors and all locations in the room are clean (hoovering has no effect), except for the locations of the patches of dirt presented in the program input. Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.  

Driving into a wall has no effect (the robot skids in place).  

Input example:   
{"roomSize": [5,5],"coords": [0,0],"patches": [[0,2],[0,3],[0,4]],"instructions": "WNNES"}  
roomSize - size of a grid (X and Y length)  
coords - hoover start position (X and Y coordinates)  
patches - dirt patches position (X and Y coordinates)  
instructions - chars, dedicated to show direction for hoover movements  

Output example: 
{"coords": [1,1],"patches": 1}  
coords - hoover final position  
patches - collected dirt patches  

## App executing 
To build your application simply run:

    $ mvn package

And then run your app using the java command:

    $ java -jar target/HooverControl-0.0.1-SNAPSHOT.jar

Your application should start up on port 8443 (HTTPS).

TODO добавить инфо про параметры окружения. Но после контейнера смысл пропадёт уже

## App using  
Open OpenAPI UI https://localhost:8443/swagger-ui/index.html and interact with endpoint
