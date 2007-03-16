-- Author: eperico
with interfaces.C, package_types;
use interfaces.C, package_types;

package package_constantes is
    
	nbBusStop : constant int := 10;

	nbBus : constant int := 20;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	periode : constant duration := 2.0;
    
    -- distance totale entre deux arrêts de bus (1000m)
    totalDistance : constant int := 1000;
	
end package_constantes; 
