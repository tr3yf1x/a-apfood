package com.nudelbaron

public class OrderFactory(val Driver : Driver)
{
    fun addOrder( _customer : Customer, _meal : Meal)
    {
        if(Driver.orders.filter{it.value.Meal.Name == _meal.Name && it.value.Customer.Name == _customer.Name}.isEmpty() )
        {
            Driver.orders.put(UuidClass().Uuid, Order(_customer, _meal))
        }
    }

    fun addOrder(_content : Map<String, String>)
    {
        val mealFactory = getMealFactory()
        val customerFactory = getCustomerFactory()

        if(mealFactory.checkKeys(_content) && customerFactory.checkKeys(_content))
        {
            val meal = mealFactory.addMeal(mutableListOf(), _content).first()
            val customer = customerFactory.addCustomer(mutableListOf(), _content).first()
            this.addOrder(customer, meal)
        }
    }

    fun getMealFactory() : MealFactory
    {
        return MealFactory()
    }

    fun getCustomerFactory() : CustomerFactory
    {
        return CustomerFactory()
    }
}