package com.nudelbaron

import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.content.PartData
import io.ktor.http.*
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.response.*
import io.ktor.server.engine.*

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        var drivers = mutableListOf<Driver>()

        val PORT = 1330

        embeddedServer(Netty, PORT) {
            routing {
                get("/meal/") {
                    call.respondText(DriverFactory().getMeals(drivers).map { it.Name }.toString(), ContentType.Text.Html)
                }
                get("/meal/{mealName}") {
                    val mealName = call.parameters!!["mealName"].toString()
                    call.respondText(
                            DriverFactory().getMeals(drivers, mealName).map { it.Name }.toString()
                            , ContentType.Text.Html)
                }
                get("/customer") {
                    call.respondText(
                            DriverFactory().getCustomers(drivers).toString()
                            , ContentType.Text.Html)

                }
                get("/customers/{customerName}") {
                    val customerName = call.parameters?.get("customerName").toString()
                    call.respondText(
                            DriverFactory().getCustomers(drivers, customerName).toString()
                            , ContentType.Text.Html)
                }
                get("/driver")
                {
                    call.respondText { drivers.map { it.Name }.toString() }
                }

                post("/driver")
                {
                    //name, location, deadline
                    val multipart = call.receiveMultipart()
                    val content = HashMap<String, String>()

                    call.respondWrite {
                        if (!call.request.isMultipart()) {
                            appendln("Not a multipart request")
                        } else {
                            while (true) {
                                val part = multipart.readPart() ?: break
                                when (part) {
                                    is PartData.FormItem ->
                                        content.put(part.name.orEmpty(), part.value)
                                }
                                part.dispose()
                            }
                        }
                        content.map { "${it.key} - ${it.value}" }.forEach { appendln(it) }
                        drivers = DriverFactory().addDriver(drivers, content).toMutableList()
                    }
                }
                post("/order")
                {

                    val multipart = call.receiveMultipart()
                    val content = HashMap<String, String>()

                    call.respondWrite {
                        if (!call.request.isMultipart()) {

                        } else {
                            while (true) {
                                val part = multipart.readPart() ?: break
                                when (part) {
                                    is PartData.FormItem ->
                                        content.put(part.name.orEmpty(), part.value)
                                }
                                part.dispose()
                            }
                        }
                        DriverFactory().addOrder(drivers, content)
                    }
                }

                get("/order/{name}") {
                    val name = call.parameters!!["name"].toString()
                    call.respondText(
                            DriverFactory().getOrders(drivers, name).map { it.toString() }.toString()
                            , ContentType.Text.Html)
                }
                get("/order") {
                    call.respondText(
                            DriverFactory().getOrders(drivers).map { it.toString() }.toString()
                            , ContentType.Text.Html)
                }
            }
        }.start(wait = true)
    }

}
