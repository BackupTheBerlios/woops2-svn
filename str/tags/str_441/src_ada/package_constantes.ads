-- Author: eperico
with interfaces.C;
use interfaces.C;

package package_constantes is
    
    -- nombre d'arrêts de bus contenu sur une ligne
	NB_BUSSTOP : constant integer := 20;

    -- nombre de bus dans le résau
	NB_BUS : constant integer := 20;

	-- periode d'envoi de la position d'un bus au centre (en secondes)
	PERIOD : constant duration := 2.0;
    
    -- delai d'attente d'un bus à un arrêt de bus
    WAITING_TIME : constant duration := 4.0;
    
    -- distance totale entre deux arrêts de bus (300m)
    TOTALDISTANCE : constant C_float := 300.0;
	
end package_constantes; 
