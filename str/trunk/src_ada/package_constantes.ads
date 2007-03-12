with interfaces.C, package_types;
use interfaces.C, package_types;

package package_constantes is
    
	nbLines : constant int := 5;
    
	nbBusStop : constant int := 20;

	nbBus : constant int := 10;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	periode : constant duration := 20.0;
	
end package_constantes; 
