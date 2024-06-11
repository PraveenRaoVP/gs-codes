package android.caged.videoapplicationapp.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

open class DatabaseEventListener : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}