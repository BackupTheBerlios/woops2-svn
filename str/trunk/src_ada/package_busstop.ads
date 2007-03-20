-- Author: eperico
with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;

package package_busstop is

    --------------------------
    -- objet protégé BusStop
    --------------------------
    protected type pt_BusStop is
        
        function getBusStopId return int;
        function getLineId return int;
        
        procedure setBusStopId(id : in int);
        procedure setLineId(id : in int);              
        
    private
        BusStopId : int;
        LineId : int; 
        -- L'attribut sera un tableau de lignes dans le cas ou les lignes se croisent
    end pt_BusStop;
	
end package_busstop;
