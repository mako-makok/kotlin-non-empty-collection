package makomakok.nonemptycollection

interface NonEmptyCollection<out E> : Collection<E> {
    val head: E

    override fun isEmpty(): Boolean = false

    fun a() {
        listOf(1).map { 1 to "" }.unzip()
    }
}
