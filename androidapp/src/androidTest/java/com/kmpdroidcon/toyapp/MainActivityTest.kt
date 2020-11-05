package com.kmpdroidcon.toyapp

import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule =
        ActivityTestRule(
            MainActivity::class.java,
            true, true
        )

    @Test
    fun testAddTodo() {
        onView(withId(R.id.todo_content_edittext))
            .perform(ViewActions.typeText("Play Fifa"), closeSoftKeyboard())

        // FIXME, this is just to show the issue please remove
        val todoUiItem = listOf(returnTodoItem())[0]
        println("Printing ${todoUiItem.timestamp}")
        onView(withId(R.id.create_todo_button))
            .perform(click())
    }

    fun returnTodoItem(): TodoUiItem {
        return TodoUiItem("12345L", "Play Fifa")
    }
}