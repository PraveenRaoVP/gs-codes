package android.caged.videoapplicationapp.model

data class Data(
    val sender: String? = null,
    val target: String? = null,
    val type: DataModelType,
    val data: String? = null,
    val timeStamp: Long = System.currentTimeMillis()
)

fun Data.isValid(): Boolean {
    return System.currentTimeMillis() - timeStamp < 60000
}