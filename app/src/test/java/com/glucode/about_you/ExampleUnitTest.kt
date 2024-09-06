package com.glucode.about_you

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats
import com.glucode.about_you.engineers.models.Answer
import com.glucode.about_you.engineers.models.Question
import com.glucode.about_you.viewmodel.AboutViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AboutViewModel

    private val engineers = listOf(
        Engineer(
            name = "Reenen",
            role = "Dev manager",
            defaultImageName = "",
            quickStats = QuickStats(years = 6, coffees = 5400, bugs = 1800),
            questions = listOf(
                Question.One(Answer("6am", 0)),
                Question.Two(Answer("10 to 15 years old", 1)),
                Question.Three(Answer("Python", 0)),
                Question.Four(Answer("Every few months", 0)),
                Question.Five(Answer("Watch or read a tutorial", 3))
            )
        ),
        Engineer(
            name = "Wilmar",
            role = "Head of Engineering",
            defaultImageName = "",
            quickStats = QuickStats(years = 15, coffees = 4000, bugs = 4000),
            questions = listOf(
                Question.One(Answer("midnight", 3)),
                Question.Two(Answer("10 to 15 years old", 1)),
                Question.Three(Answer("Python", 0)),
                Question.Four(Answer("Every few months", 0)),
                Question.Five(Answer("Call a coworker or friend", 2))
            )
        ),
        Engineer(
            name = "Eben",
            role = "Head of Testing",
            defaultImageName = "",
            quickStats = QuickStats(years = 14, coffees = 1000, bugs = 100),
            questions = listOf(
                Question.One(Answer("midnight", 3)),
                Question.Two(Answer("10 to 15 years old", 0)),
                Question.Three(Answer("Kotlin", 1)),
                Question.Four(Answer("Every few months", 0)),
                Question.Five(Answer("Watch or read a tutorial", 3))
            )
        ),
        Engineer(
            name = "Stefan",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 7, coffees = 9000, bugs = 700),
            questions = listOf(
                Question.One(Answer("6am", 0)),
                Question.Two(Answer("21 to 25 years old", 3)),
                Question.Three(Answer("Ruby", 3)),
                Question.Four(Answer("Once a year", 1)),
                Question.Five(Answer("Visit Stack Overflow", 0))
            )
        ),
        Engineer(
            name = "Brandon",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 9, coffees = 99999, bugs = 99999),
            questions = listOf(
                Question.One(Answer("6am", 0)),
                Question.Two(Answer("10 to 15 years old", 1)),
                Question.Three(Answer("C++", 5)),
                Question.Four(Answer("Every few months", 0)),
                Question.Five(Answer("Visit Stack Overflow", 0))
            )
        ),
        Engineer(
            name = "Henri",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 10, coffees = 1800, bugs = 1000),
            questions = listOf(
                Question.One(Answer("6am", 0)),
                Question.Two(Answer("10 to 15 years old", 0)),
                Question.Three(Answer("Rust", 6)),
                Question.Four(Answer("Every few months", 0)),
                Question.Five(Answer("Go down a google rabbit hole", 4))
            )
        )
    )

    @Before
    fun setUp() {
        viewModel = AboutViewModel()
    }

    @Test
    fun testSortEngineersByYears() {
        viewModel._engineerList.value = engineers
        viewModel.sortEngineersByYears()
        val sortedList = viewModel.engineerList.value
        assertEquals(listOf("Reenen", "Stefan", "Henri", "Eben", "Wilmar", "Brandon"), sortedList?.map { it.name })
    }

    @Test
    fun testSortEngineersByCoffees() {
        viewModel._engineerList.value = engineers
        viewModel.sortEngineersByCoffees()
        val sortedList = viewModel.engineerList.value
        assertEquals(listOf("Eben", "Henri", "Wilmar", "Reenen", "Stefan", "Brandon"), sortedList?.map { it.name })
    }

    @Test
    fun testSortEngineersByBugs() {
        viewModel._engineerList.value = engineers
        viewModel.sortEngineersByBugs()
        val sortedList = viewModel.engineerList.value
        assertEquals(listOf("Eben", "Henri", "Stefan", "Reenen", "Wilmar", "Brandon"), sortedList?.map { it.name })
    }
}
