package com.nudelbaron

import java.util.*

class Meal(val Name: String) : UuidClass()
{
    val miscs = hashMapOf<UUID, Misc>()

    fun print()
    {
        println("$Name")
        miscs.values.forEach{it.print()}
    }

    override fun toString() : String
    {
        return Name
    }

    fun addMisc(_misc : String) : Boolean
    {
        return this.addMisc(Misc(_misc))
    }

    fun addMisc(_misc : Misc) : Boolean
    {
        if(miscs.containsKey(_misc.Uuid))
        {
            return false;
        }

        miscs[_misc.Uuid] = _misc
        return true;
    }

    fun removeMisc(_misc: Misc) : Boolean
    {
        return (miscs.remove(_misc.Uuid) != null)
    }
}