with package_types, Text_io, interfaces.C;
use package_types, Text_io, interfaces.C;

package package_line is

    -- definition du type Line qui est une tache
    -- idLine est l'identifiant de la ligne
    task type tt_line (number : int) is
       entry getNumber(n : out int);
       entry addBusStop(ptr_busStop : t_ptr_tt_busStop);
    end tt_line;
    
   type t_ptr_tt_line is access tt_line;
    
end package_line;
