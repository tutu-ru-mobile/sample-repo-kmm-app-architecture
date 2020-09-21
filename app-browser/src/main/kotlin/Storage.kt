import kotlinx.html.currentTimeMillis

interface Storage {
    fun getItem(key: String): StoreItem?
    fun saveItem(key: String, item: StoreItem)
}

data class StoreItem(
    val successCount: Int = 0,
    val failCount: Int = 0,
    val changedUnixTime: Int = 0
)

object BrowserStorage : Storage {

    override fun getItem(key: String): StoreItem? {
        return null
    }

    override fun saveItem(key: String, item: StoreItem) {

    }

    fun clear() {

    }

}

fun currentUnixTime() = (currentTimeMillis() / 1000).toInt()
