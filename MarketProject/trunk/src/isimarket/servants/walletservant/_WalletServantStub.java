package isimarket.servants.walletservant;


/**
* isimarket/model/servants/walletservant/_WalletServantStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 31 mars 2008 16 h 56 CEST
*/

public class _WalletServantStub extends org.omg.CORBA.portable.ObjectImpl implements isimarket.servants.walletservant.WalletServant
{

  public isimarket.model.Wallet getWallet (String operatorLogin, String password)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getWallet", true);
                $out.write_string (operatorLogin);
                $out.write_string (password);
                $in = _invoke ($out);
                isimarket.model.Wallet $result = isimarket.model.WalletHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getWallet (operatorLogin, password        );
            } finally {
                _releaseReply ($in);
            }
  } // getWallet

  public isimarket.model.Action[] getActionList (int walletId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActionList", true);
                $out.write_long (walletId);
                $in = _invoke ($out);
                isimarket.model.Action $result[] = isimarket.model.ActionListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActionList (walletId        );
            } finally {
                _releaseReply ($in);
            }
  } // getActionList

  public isimarket.model.Action[] getAction (int walletId, int actionId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getAction", true);
                $out.write_long (walletId);
                $out.write_long (actionId);
                $in = _invoke ($out);
                isimarket.model.Action $result[] = isimarket.model.ActionListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getAction (walletId, actionId        );
            } finally {
                _releaseReply ($in);
            }
  } // getAction

  public isimarket.model.Action buyAction (int walletId, String actionTypeCode, int quantity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("buyAction", true);
                $out.write_long (walletId);
                $out.write_string (actionTypeCode);
                $out.write_long (quantity);
                $in = _invoke ($out);
                isimarket.model.Action $result = isimarket.model.ActionHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return buyAction (walletId, actionTypeCode, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // buyAction

  public void sellAction (int walletActionId, String actionTypeCode, int quantity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sellAction", true);
                $out.write_long (walletActionId);
                $out.write_string (actionTypeCode);
                $out.write_long (quantity);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sellAction (walletActionId, actionTypeCode, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // sellAction

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/walletservant/WalletServant:1.0"};

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
} // class _WalletServantStub