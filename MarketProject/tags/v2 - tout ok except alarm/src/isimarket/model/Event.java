package isimarket.model;


/**
* isimarket/model/Event.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* mardi 8 avril 2008 19 h 22 CEST
*/

public final class Event implements org.omg.CORBA.portable.IDLEntity
{
  public int eventId = (int)0;
  public float price = (float)0;
  public String date = null;
  public isimarket.model.ActionType actiontype = null;

  public Event ()
  {
  } // ctor

  public Event (int _eventId, float _price, String _date, isimarket.model.ActionType _actiontype)
  {
    eventId = _eventId;
    price = _price;
    date = _date;
    actiontype = _actiontype;
  } // ctor

} // class Event
