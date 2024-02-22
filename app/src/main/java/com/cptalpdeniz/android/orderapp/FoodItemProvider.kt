package com.cptalpdeniz.android.orderapp

import android.content.res.Resources

/*
this class provide fake datasource locally
 */

class FoodItemProvider {
    companion object {
        val foodList = listOf(
            FoodItem(
                "Egg Fried Rice",
                "Vietnamese",
                R.drawable.vietnam,
                "Stir-fried white rice with scrambled eggs and a dash of fish sauce.",
                R.drawable.food_image_2,
                "chicken, corn, peas, carrot, green onion, onion, garlic, eggs, white rice, canola oil, fish sauce."
            ),
            FoodItem(
                "Tofu Fried Rice",
                "Vietnamese",
                R.drawable.vietnam,
                "Stir-fried white rice with crumbled tofu and a dash of tamari sauce.",
                R.drawable.food_image_2,
                "tofu, corn, peas, carrot, green onion, onion, garlic, white rice, canola oil, tamari sauce."
            ),
            FoodItem(
                "Kung Pao Chicken",
                "Chinese",
                R.drawable.china,
                "White rice with chicken and stir-fried veggies.",
                R.drawable.food_image_3,
                "chicken, leek onion, carrot, cucumber, sichuan pepper, canola oil, dried chili, ginger, chicken thigh, sesame oil, salt, white pepper, starch, white sugar, cooking wine, soy sauce, water, starch, rice viner, white rice, peanuts, garlic."
            ),
            FoodItem(
                "Kung Pao Tofu",
                "Chinese",
                R.drawable.china,
                "White rice with tofu and stir-fried veggies.",
                R.drawable.food_image_3,
                "tofu, leek onion, carrot, cucumber, sichuan pepper, canola oil, dried chili, ginger, chicken thigh, sesame oil, salt, white pepper, starch, white sugar, cooking wine, tamari sauce, water, starch, rice viner, white rice, peanuts, garlic."
            ),
            FoodItem(
                "Pork Yaki Udon",
                "Japanese",
                R.drawable.korea,
                "Stir-fried udon noodles with pork, veggies in a soy-mirin sauce.",
                R.drawable.food_image_4,
                "pork, cabbage, carrot, onion, pork chop, udon noodles, soy sauce, sake, mirin, canola oil, salt, black pepper."
            ),
            FoodItem(
                "Tofu Yaki Udon",
                "Japanese",
                R.drawable.korea,
                "Stir-fried udon noodles with tofu, veggies in a soy-mirin sauce",
                R.drawable.food_image_4,
                "tofu, cabbage, carrot, onion, pork chop, udon noodles, tamari sauce, sake, mirin, canola oil, salt, black pepper.",

                ),

            FoodItem(
                "Pancit Canton",
                "Philipino",
                R.drawable.philipine,
                "Stir-fried udon noodles with tofu, veggies in a soy-mirin sauce",
                R.drawable.food_image_5,
                "chicken, green beans, carrot, bell pepper, cabbage, garlic, onion, oyster sauce, soy sauce, black pepper, pancit canton noodles, canola oil, lime"
            ),
            FoodItem(
                "Vegan Pancit Canton",
                "Philipino",
                R.drawable.philipine,
                "Stir-fried udon noodles with tofu, veggies in a soy-mirin sauce",
                R.drawable.food_image_5,
                "tofu, green beans, carrot, bell pepper, cabbage, garlic, onion, hoisin sauce, tamari sauce, black pepper, pancit canton noodles, canola oil, lime",

                ),
            FoodItem(
                "Bibimbap",
                "Korean",
                R.drawable.japan,
                "Sushi rice with veggies, pork, a fried egg, and spicy gochujang sauce.",
                R.drawable.food_image,
                "pork, egg, sushi rice, sesame seeds, green apple, garlic, soy sauce, white sugar, sesame oil, carrot, zucchini, spinach, bean sprouts, canola oil, salt, soy sauce, fish sauce, gochujang, mirin, rice vinegar."
            ),
            FoodItem(
                "Vegan Bibimbap",
                "Korean",
                R.drawable.japan,
                "Sushi rice with veggies, tofu, and spicy gochujang sauce.",
                R.drawable.food_image,
                "tofu, sushi rice, sesame seeds, green apple, garlic, soy sauce, white sugar, sesame oil, carrot, zucchini, spinach, bean sprouts, canola oil, salt, soy sauce, fish sauce, gochujang, mirin, rice vinegar."
            ),
        )
    }
}