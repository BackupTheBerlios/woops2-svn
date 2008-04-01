package isimarket.servants.administrationservant;


/**
* isimarket/model/servants/administrationservant/_AdministrationServantStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 1 avril 2008 19 h 14 CEST
*/

public class _AdministrationServantStub extends org.omg.CORBA.portable.ObjectImpl implements isimarket.servants.administrationservant.AdministrationServant
{

  public void updateCash (String operatorLogin, float newCash)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateCash", true);
                $out.write_string (operatorLogin);
                $out.write_float (newCash);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                updateCash (operatorLogin, newCash        );
            } finally {
                _releaseReply ($in);
            }
  } // updateCash

  public void createOperator (String operatorLogin, String password, float initalCash)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createOperator", true);
                $out.write_string (operatorLogin);
                $out.write_string (password);
                $out.write_float (initalCash);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createOperator (operatorLogin, password, initalCash        );
            } finally {
                _releaseReply ($in);
            }
  } // createOperator

  public void createWallet (float initalCash)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createWallet", true);
                $out.write_float (initalCash);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createWallet (initalCash        );
            } finally {
                _releaseReply ($in);
            }
  } // createWallet

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/administrationservant/AdministrationServant:1.0"};

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
} // class _AdministrationServantStub
