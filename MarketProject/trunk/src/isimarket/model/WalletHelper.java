package isimarket.model;


/**
* isimarket/model/WalletHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 24 mars 2008 12 h 24 CET
*/

abstract public class WalletHelper
{
  private static String  _id = "IDL:isimarket/model/Wallet/Wallet:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.model.Wallet that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.model.Wallet extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "wallet_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[1] = new org.omg.CORBA.StructMember (
            "cash",
            _tcOf_members0,
            null);
          _tcOf_members0 = isimarket.model.ActionHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (isimarket.model.ActionListHelper.id (), "ActionList", _tcOf_members0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "actions",
            _tcOf_members0,
            null);
          _tcOf_members0 = isimarket.model.AlarmHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (isimarket.model.AlarmListHelper.id (), "AlarmList", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "alarms",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (isimarket.model.WalletHelper.id (), "Wallet", _members0);
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

  public static isimarket.model.Wallet read (org.omg.CORBA.portable.InputStream istream)
  {
    isimarket.model.Wallet value = new isimarket.model.Wallet ();
    value.wallet_id = istream.read_long ();
    value.cash = istream.read_float ();
    value.actions = isimarket.model.ActionListHelper.read (istream);
    value.alarms = isimarket.model.AlarmListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.model.Wallet value)
  {
    ostream.write_long (value.wallet_id);
    ostream.write_float (value.cash);
    isimarket.model.ActionListHelper.write (ostream, value.actions);
    isimarket.model.AlarmListHelper.write (ostream, value.alarms);
  }

}
