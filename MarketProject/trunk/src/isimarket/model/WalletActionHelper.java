package isimarket.model;


/**
* isimarket/model/WalletActionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* samedi 29 mars 2008 11 h 45 CET
*/

abstract public class WalletActionHelper
{
  private static String  _id = "IDL:isimarket/model/WalletAction/WalletAction:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.model.WalletAction that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.model.WalletAction extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [5];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "wallet_action_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[1] = new org.omg.CORBA.StructMember (
            "buyPrice",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "buyDate",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[3] = new org.omg.CORBA.StructMember (
            "quantity",
            _tcOf_members0,
            null);
          _tcOf_members0 = isimarket.model.ActionTypeHelper.type ();
          _members0[4] = new org.omg.CORBA.StructMember (
            "actiontype",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (isimarket.model.WalletActionHelper.id (), "WalletAction", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static isimarket.model.WalletAction read (org.omg.CORBA.portable.InputStream istream)
  {
    isimarket.model.WalletAction value = new isimarket.model.WalletAction ();
    value.wallet_action_id = istream.read_long ();
    value.buyPrice = istream.read_float ();
    value.buyDate = istream.read_string ();
    value.quantity = istream.read_long ();
    value.actiontype = isimarket.model.ActionTypeHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.model.WalletAction value)
  {
    ostream.write_long (value.wallet_action_id);
    ostream.write_float (value.buyPrice);
    ostream.write_string (value.buyDate);
    ostream.write_long (value.quantity);
    isimarket.model.ActionTypeHelper.write (ostream, value.actiontype);
  }

}
