package com.nudelbaron

import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.server.engine.*

object App
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val driver = makeTestDriver()

        val PORT = 12345
        embeddedServer(Netty, PORT) {
            routing {
                get("/meals/") {
                    call.respondText ( mealsPerDriver(driver).map { it.Name }.toString(), ContentType.Text.Html )
                }
                get("/meals/{mealName}") {
                    val mealName = call.parameters!!["mealName"].toString()
                    call.respondText( mealsPerDriver(driver).filter {
                        meal -> meal.Name.contains(mealName)
                    }.map { it.Name }.toString(), ContentType.Text.Html)
                }
                get("/customers") {
                    call.respondText(driver.orders.map { it.value.Customer.Name }.toString(), ContentType.Text.Html )
                }
                get("/customers/{customerName}") {
                    val customerName = call.parameters?.get("customerName").toString()
                    call.respondText(driver.orders.filter {it.value.Customer.Name.contains(customerName)  }.map { it.value.Customer.Name }.toString(), ContentType.Text.Html )
                }
                get("/orders/{name}") {
                    val name =  call.parameters!!["name"].toString()
                    call.respondText(driver.orders.filter { order -> order.value.Customer.Name == name}.map { Pair<String, String>(it.value.Meal.Name, it.value.Meal.miscs.map { it.value.Title }.toString()) }.toString(), ContentType.Text.Html )
                }
            }
        }.start(wait = true)
    }

    fun makeTestDriver() : Driver
    {

        val driver =  Driver("Michi", "Döner", "12:00")

        driver.addOrder(Order(Customer("Jochen-G"), Meal("Fleischbox")))
        driver.addOrder(Order(Customer("Gueven"), Meal("Dürüm")))

        val mealMichiB =  Meal("Döner")
        val michiB  = Order(Customer("Michael-B"), mealMichiB)

        val mealMichiS = Meal("Döner")
        mealMichiS.addMisc("Scharf")
        mealMichiS.addMisc("ohne Tomate")
        val michiS = Order(Customer("Michael-S"), mealMichiS)

        driver.addOrder(michiB)
        driver.addOrder(michiS)

        mealMichiB.addMisc(Misc("Scharf"))
        mealMichiB.addMisc(Misc("Ohne Tomate"))

        return driver
    }

    fun mealsPerDriver(_driver : Driver) : List<Meal>
    {
        return _driver.orders.map { it.value }.map { it.Meal }
    }


/*
fun miscPerDriver(_driver : Driver, _meal : Meal) : List<Misc>
{
 return miscPerMeals(mealsPerDriver(_driver).filter{it.Name.toUpperCase() == _meal.Name.toUpperCase()})
}

fun miscPerMeals(_meals : List<Meal>) : List<Misc>
{
 return _meals.map { it.miscs.values }.flatMap { it.filter{true} }
}
fun miscPerDriver(_driver : Driver) : List<Misc>
{
 return miscPerMeals(mealsPerDriver(_driver))
}
*/
}