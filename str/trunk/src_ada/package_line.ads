with package_types, Text_io, Ada.Exceptions, interfaces.C;
use package_types, Text_io, Ada.Exceptions, interfaces.C;

package package_line is

  -- definition du type busStop qui est une tache
    -- idBusStop est l'identifiant du busStop
  -- initialPosition est la position du busStop
  task type tt_line (number : int) is
       entry getNumber(n : out int);
      entry addBusStop(ptr_busStop : t_ptr_t_busStop);
    end tt_line;
    
   type t_ptr_tt_line is access tt_line;
    
end package_line;
