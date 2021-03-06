package isimarket.servants.actiontypeservant;


/**
* isimarket/model/servants/actiontypeservant/_ActionTypeServantStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* dimanche 4 mai 2008 19 h 59 CEST
*/

public class _ActionTypeServantStub extends org.omg.CORBA.portable.ObjectImpl implements isimarket.servants.actiontypeservant.ActionTypeServant
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

  public isimarket.model.ActionType getActionType (String actionTypeCode)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActionType", true);
                $out.write_string (actionTypeCode);
                $in = _invoke ($out);
                isimarket.model.ActionType $result = isimarket.model.ActionTypeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActionType (actionTypeCode        );
            } finally {
                _releaseReply ($in);
            }
  } // getActionType

  public isimarket.model.ActionType getLastInsertedActionType ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLastInsertedActionType", true);
                $in = _invoke ($out);
                isimarket.model.ActionType $result = isimarket.model.ActionTypeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLastInsertedActionType (        );
            } finally {
                _releaseReply ($in);
            }
  } // getLastInsertedActionType

  public void createActionType (String code, String label, String introductionDate, float introductionPrice, int quantity, float currentPrice)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createActionType", true);
                $out.write_string (code);
                $out.write_string (label);
                $out.write_string (introductionDate);
                $out.write_float (introductionPrice);
                $out.write_long (quantity);
                $out.write_float (currentPrice);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createActionType (code, label, introductionDate, introductionPrice, quantity, currentPrice        );
            } finally {
                _releaseReply ($in);
            }
  } // createActionType

  public void updateActionTypeQuantity (String code, int quantity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateActionTypeQuantity", true);
                $out.write_string (code);
                $out.write_long (quantity);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                updateActionTypeQuantity (code, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // updateActionTypeQuantity

  public void updateActionTypeCurrentPrice (String code, float currentPrice)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateActionTypeCurrentPrice", true);
                $out.write_string (code);
                $out.write_float (currentPrice);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                updateActionTypeCurrentPrice (code, currentPrice        );
            } finally {
                _releaseReply ($in);
            }
  } // updateActionTypeCurrentPrice

  public void deleteActionType (String actionTypeCode)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteActionType", true);
                $out.write_string (actionTypeCode);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                deleteActionType (actionTypeCode        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteActionType

  public isimarket.model.Event[] getEvents (String actionTypeCode)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getEvents", true);
                $out.write_string (actionTypeCode);
                $in = _invoke ($out);
                isimarket.model.Event $result[] = isimarket.model.EventListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getEvents (actionTypeCode        );
            } finally {
                _releaseReply ($in);
            }
  } // getEvents

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/actiontypeservant/ActionTypeServant:1.0"};

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
} // class _ActionTypeServantStub
