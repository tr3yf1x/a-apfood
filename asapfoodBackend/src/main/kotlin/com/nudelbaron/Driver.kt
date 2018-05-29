package com.nudelbaron

import java.util.*

class Driver(val Name: String, val Location: String, val Deadline: String)
{
    val orders = HashMap<UUID, Order>()

    fun print()
    {
        println("$Name;$Location;$Deadline")
        orders.forEach{it.value.print()}
    }

    fun addOrder(_order : Order) : Boolean
    {
        if(orders.containsKey(_order.Uuid))
        {
            return false;
        }
        orders[_order.Uuid] =  _order
        return true;
    }

    fun removeOrder(_order: Order) : Boolean
    {
        return (orders.remove(_order.Uuid) != null)
    }
}