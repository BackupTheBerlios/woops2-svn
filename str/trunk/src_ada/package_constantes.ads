-- Author: eperico
with interfaces.C;
use interfaces.C;

package package_constantes is
    
	nbBusStop : constant integer := 13;

	nbBus : constant int := 20;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	periode : constant duration := 2.0;
    
    -- distance totale entre deux arrêts de bus (1000m)
    --totalDistance : constant int := 1000;
	
end package_constantes; 
