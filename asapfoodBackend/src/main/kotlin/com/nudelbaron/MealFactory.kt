package com.nudelbaron

class MealFactory()
{

    val MEALKEY = "meal"
    val MISCKEY = "misc"

    fun checkKeys(_content : Map<String, String>) : Boolean
    {
        return _content.containsKey(MEALKEY)
    }

    fun addMeal(_meals : MutableList<Meal>,  _content : Map<String, String>) : MutableList<Meal>
    {

        val mealName = _content.get(MEALKEY).orEmpty()

        val miscs = _content.filter { it.key == MISCKEY}.map { it.value }
        if(!mealName.isEmpty())
        {
            val meal = Meal(mealName)
            miscs.forEach{ meal.addMisc(it)}
            _meals.add(meal)
        }

        return _meals
    }
}