package isimarket.servants.actionservant;


/**
* isimarket/model/servants/actionservant/_ActionServantStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 24 mars 2008 12 h 24 CET
*/

public class _ActionServantStub extends org.omg.CORBA.portable.ObjectImpl implements isimarket.servants.actionservant.ActionServant
{

  public isimarket.model.ActionType[] getActionTypeList ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActionTypeList", true);
                $in = _invoke ($out);
                isimarket.model.ActionType $result[] = isimarket.model.ActionTypeListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActionTypeList (        );
            } finally {
                _releaseReply ($in);
            }
  } // getActionTypeList

  public isimarket.model.Action[] buyAction (String actionTypeLabel, int quantity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("buyAction", true);
                $out.write_string (actionTypeLabel);
                $out.write_long (quantity);
                $in = _invoke ($out);
                isimarket.model.Action $result[] = isimarket.model.ActionListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return buyAction (actionTypeLabel, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // buyAction

  public void sellAction (String actionTypeLabel, int quantity, int actions_id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sellAction", true);
                $out.write_string (actionTypeLabel);
                $out.write_long (quantity);
                $out.write_long (actions_id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sellAction (actionTypeLabel, quantity, actions_id        );
            } finally {
                _releaseReply ($in);
            }
  } // sellAction

  public isimarket.model.ActionType getActionType (String label)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActionType", true);
                $out.write_string (label);
                $in = _invoke ($out);
                isimarket.model.ActionType $result = isimarket.model.ActionTypeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActionType (label        );
            } finally {
                _releaseReply ($in);
            }
  } // getActionType

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/actionservant/ActionServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _ActionServantStub
