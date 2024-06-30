//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `smarthouse.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package smarthouse;

public class LiquidFull extends com.zeroc.Ice.UserException
{
    public LiquidFull()
    {
        this.message = "";
    }

    public LiquidFull(Throwable cause)
    {
        super(cause);
        this.message = "";
    }

    public LiquidFull(String message)
    {
        this.message = message;
    }

    public LiquidFull(String message, Throwable cause)
    {
        super(cause);
        this.message = message;
    }

    public String ice_id()
    {
        return "::smarthouse::LiquidFull";
    }

    public String message;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::smarthouse::LiquidFull", -1, true);
        ostr_.writeString(message);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        message = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = 2057699941L;
}