java -jar -Xmx4096m osmparser-0.13.jar -i buildings=university -f map.osm -o buildings.json -q
java -jar -Xmx4096m osmparser-0.13.jar -i highway route -f map.osm -o roads.json -q
pause