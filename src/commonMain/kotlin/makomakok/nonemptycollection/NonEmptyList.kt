package makomakok.nonemptycollection

typealias Nel<E> = NonEmptyList<E>

class NonEmptyList<out E> private constructor(
    private val elements: List<E>
) : NonEmptyCollection<E>, List<E> by elements {

    constructor(head: E, tail: List<E>) : this(listOf(head) + tail)

    override val size: Int get() = elements.size
    override val head: E get() = elements.first()
    val tail get() = elements.drop(1)

    inline fun <T> map(transform: (E) -> T): NonEmptyList<T> =
        NonEmptyList(head.let(transform), tail.map(transform))

    inline fun <T> flatMap(transform: (E) -> NonEmptyList<T>): NonEmptyList<T> =
        this.toList()
            .flatMap { transform(it) }
            .let { it.toNonEmptyListOrNull()!! }

    inline fun <K> distinctBy(selector: (E) -> K): NonEmptyList<E> =
        toList().distinctBy(selector).toNonEmptyListOrNull()!!

    fun distinct(): NonEmptyList<E> = toList().distinct().toNonEmptyListOrNull()!!

    fun <R> zip(other: NonEmptyList<R>): NonEmptyList<Pair<E, R>> =
        NonEmptyList(head to other.head, tail.zip(other.tail))

    fun toList(): List<E> = elements

    operator fun plus(element: @UnsafeVariance E): NonEmptyList<E> =
        NonEmptyList(this.elements + listOf(element))

    operator fun plus(elements: Iterable<@UnsafeVariance E>): NonEmptyList<E> =
        NonEmptyList(this.elements + elements)

    operator fun plus(elements: NonEmptyList<@UnsafeVariance E>): NonEmptyList<E> =
        NonEmptyList(this.elements + elements.elements)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NonEmptyList<*>) return false

        if (elements != other.elements) return false
        if (size != other.size) return false
        if (head != other.head) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elements.hashCode()
        result = 31 * result + size
        result = 31 * result + (head?.hashCode() ?: 0)
        return result
    }

    override fun isEmpty(): Boolean = false
}

fun <T, R> NonEmptyList<Pair<T, R>>.unzipNel(): Pair<NonEmptyList<T>, NonEmptyList<R>> {
    return unzip().let {
        it.first.toNonEmptyListOrNull()!! to it.second.toNonEmptyListOrNull()!!
    }
}

fun <E> NonEmptyList<NonEmptyList<E>>.flattenNel(): NonEmptyList<E> = this.flatMap { it }

fun <E> nonEmptyListOf(element: E, vararg elements: E): NonEmptyList<E> = NonEmptyList(element, elements.asList())
fun <E> Collection<E>.toNonEmptyListOrNull(): NonEmptyList<E>? {
    return if (this.isEmpty()) {
        null
    } else {
        NonEmptyList(first(), drop(1))
    }
}
