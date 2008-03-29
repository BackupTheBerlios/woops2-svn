package isimarket.model;


/**
* isimarket/model/Event.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* samedi 29 mars 2008 11 h 45 CET
*/

public final class Event implements org.omg.CORBA.portable.IDLEntity
{
  public int event_id = (int)0;
  public float Price = (float)0;
  public String date = null;
  public isimarket.model.ActionType actiontype = null;

  public Event ()
  {
  } // ctor

  public Event (int _event_id, float _Price, String _date, isimarket.model.ActionType _actiontype)
  {
    event_id = _event_id;
    Price = _Price;
    date = _date;
    actiontype = _actiontype;
  } // ctor

} // class Event
