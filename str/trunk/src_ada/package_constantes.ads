with interfaces.C, package_types;
use interfaces.C, package_types;

package package_constantes is
    
	nbLines : constant int := 5;
    
	nbBusStop : constant int := 20;

	nbBus : constant int := 10;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	periode : constant duration := 2.0;
    
    -- distance totale entre deux arrêts de bus (1000m)
    Totaldistance : constant int := 1000;
	
end package_constantes; 
