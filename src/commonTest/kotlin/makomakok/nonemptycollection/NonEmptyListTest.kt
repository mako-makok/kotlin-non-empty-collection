package makomakok.nonemptycollection

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class NonEmptyListTest {

    @Test
    fun map() {
        // given
        val nel = nonEmptyListOf(1, 2, 3)

        // when
        val actual = nel.map { it * 2 }

        // then
        actual shouldBe nonEmptyListOf(2, 4, 6)
    }

    @Test
    fun flatMap() {
        // given
        val nel = nonEmptyListOf(1, 2, 3)

        // when
        val actual = nel.flatMap { nonEmptyListOf(it, it) }

        // then
        actual shouldBe nonEmptyListOf(1, 1, 2, 2, 3, 3)
    }

    @Test
    fun distinctBy() {
        // given
        val nel = nonEmptyListOf(1, 2, 3, 2)

        // when
        val actual = nel.distinctBy { it }

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3)
    }

    @Test
    fun distinct() {
        // given
        val nel = nonEmptyListOf(1, 2, 3, 2)

        // when
        val actual = nel.distinct()

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3)
    }

    @Test
    fun zip() {
        // given
        val nel1 = nonEmptyListOf(1, 2, 3)
        val nel2 = nonEmptyListOf("a", "b", "c")

        // when
        val actual = nel1.zip(nel2)

        // then
        actual shouldBe nonEmptyListOf(1 to "a", 2 to "b", 3 to "c")
    }

    @Test
    fun plus() {
        // given
        val nel = nonEmptyListOf(1, 2, 3)

        // when
        val actual = nel + 4

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3, 4)
    }

    @Test
    fun plusIterable() {
        // given
        val nel = nonEmptyListOf(1, 2, 3)

        // when
        val actual = nel + listOf(4, 5)

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3, 4, 5)
    }

    @Test
    fun plusNonEmptyList() {
        // given
        val nel1 = nonEmptyListOf(1, 2, 3)
        val nel2 = nonEmptyListOf(4, 5, 6)

        // when
        val actual = nel1 + nel2

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun unzipNel() {
        // given
        val nel = nonEmptyListOf(1 to "a", 2 to "b", 3 to "c")

        // when
        val actual = nel.unzipNel()

        // then
        actual shouldBe Pair(nonEmptyListOf(1, 2, 3), nonEmptyListOf("a", "b", "c"))
    }

    @Test
    fun flattenNel() {
        // given
        val nel = nonEmptyListOf(
            nonEmptyListOf(1, 2, 3),
            nonEmptyListOf(4, 5, 6),
            nonEmptyListOf(7, 8, 9)

        )

        // when
        val actual = nel.flattenNel()

        // then
        actual shouldBe nonEmptyListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}
