package com.nudelbaron

class CustomerFactory()
{
    val CUSTOMERKEY = "customer"

    fun newCustomer(_name : String) : Customer
    {
       return Customer(_name)
    }

    fun addCustomer(_customers : MutableList<Customer>, _content : Map<String, String>) : MutableList<Customer>
    {
        if(checkKeys(_content))
        {
            val name = _content.get(CUSTOMERKEY).orEmpty()
            _customers.add(Customer(name))
        }
       return _customers
    }

    fun checkKeys(_content : Map<String, String>) : Boolean
    {
       return _content.containsKey(CUSTOMERKEY)
    }

}