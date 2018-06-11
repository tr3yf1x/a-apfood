package com.nudelbaron

class DriverFactory {
    val DRIVERKEY = "driver"
    val LOCATIONKEY = "location"
    val DEADLINEKEY = "deadline"
    val validKeys = mutableListOf<String>(DRIVERKEY, LOCATIONKEY, DEADLINEKEY)

    fun addDriver(_drivers : MutableList<Driver>, _content : HashMap<String, String>) : List<Driver>
    {
        if (checkKeys(_content)&& _drivers.filter { it.Name == _content[DRIVERKEY].orEmpty()}.isEmpty()) {
            _drivers.add(Driver(_content[DRIVERKEY].orEmpty(), _content[LOCATIONKEY].orEmpty(), _content[DEADLINEKEY].orEmpty()))

        }
        return _drivers
    }

    fun getOrderFactory(_driver : Driver) : OrderFactory
    {
        return OrderFactory(_driver)
    }

    fun checkKeys(_content: HashMap<String, String>) : Boolean
    {
        return  _content.filter { it.key in validKeys }.count() == validKeys.count()
    }

    fun addOrder(_drivers: MutableList<Driver>, _content: HashMap<String, String>)
    {
        val driverMatches = _drivers.filter { it.Name == _content.get(DRIVERKEY) }
        with(
                _drivers.filter { it.Name == _content.get(DRIVERKEY)}?.first()
        )
        {
            getOrderFactory(this).addOrder(_content)
        }
    }

    fun getMeals(_drivers: MutableList<Driver>, _name : String = String().orEmpty() ) : List<Meal>
    {
        return getMeals(_drivers).filter { it.Name.contains(_name) }.orEmpty()
    }

    fun getMeals(_drivers : List<Driver>) :  List<Meal>
    {
        return _drivers.flatMap { it.orders.values.map { it.Meal } }.orEmpty()
    }

    fun getCustomers(_drivers: MutableList<Driver>, _name: String ) : List<Customer>
    {
        return getCustomers(_drivers).filter { it.Name.contains(_name) }.orEmpty()
    }

    fun getCustomers(_drivers: MutableList<Driver> ) : List<Customer>
    {
        return _drivers.flatMap { it.orders.values.map{it.Customer} }.orEmpty()
    }

    fun getOrders(_drivers: MutableList<Driver>) : List<Order>
    {
        return _drivers.flatMap { it.orders.values }.orEmpty()
    }

    fun getOrders(_drivers: MutableList<Driver>, _customer : Customer) : List<Order>
    {
        return getOrders(_drivers).filter { it.Customer == _customer }.orEmpty()
    }

    fun getOrders(_drivers: MutableList<Driver>, _name : String) : List<Order>
    {
        return getOrders(_drivers).filter { it.Customer.Name.contains(_name) }.orEmpty()
    }
}
