package com.nudelbaron

class Order(val Customer: Customer, val Meal: Meal) : UuidClass()
{
    fun print()
    {
        Customer.print()
        Meal.print()
    }
}