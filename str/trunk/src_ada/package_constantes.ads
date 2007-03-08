with interfaces.C;
use interfaces.C;

package package_constantes is

	------------------------------------------------------
	-- constante utilisees par les busStops
	------------------------------------------------------
	
	-- capacite maximal du recorder
	capaciteBuffer : constant int := 50;

	-- nombre de lignes sur lesquelles un busStop peut se trouver 
	nbLines : constant int := 100;

	-- nombre de lignes sur lesquelles un busStop peut se trouver 
	nbBusStop : constant int := 100;

	-- nombre de lignes sur lesquelles un busStop peut se trouver 
	nbBus : constant int := 100;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	periode : constant duration := 20.0;
	
end package_constantes; 
