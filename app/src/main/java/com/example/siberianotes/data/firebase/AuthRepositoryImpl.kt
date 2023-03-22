package com.example.siberianotes.data.firebase

import android.util.Log
import com.example.siberianotes.domain.model.NetworkResult
import com.example.siberianotes.domain.model.UserModel
import com.example.siberianotes.domain.repository.AuthRepository
import com.example.siberianotes.utils.Constants
import com.example.siberianotes.utils.Constants.REFERENCE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,

            private val firebaseFirestore: FirebaseFirestore,
            private val database: DatabaseReference = Firebase
                .database(REFERENCE).reference
        .child(firebaseAuth.currentUser?.uid.toString())


) : AuthRepository{

    val TAG = "AuthRepositoryImpl"

    override suspend fun firebaseSignUp(user: UserModel): Flow<NetworkResult<Boolean>> {
       return flow {
           var isSuccess = false

           emit(NetworkResult.Loading())

           try {
               firebaseAuth.createUserWithEmailAndPassword(user.login, user.password)
                   .addOnCompleteListener { task ->
                       isSuccess = if(task.isSuccessful){
                           Log.d("check", "createUserWithEmailAndPassword:: success")
                           val firebaseUser = firebaseAuth.currentUser
                           if(firebaseUser != null){
                               user.userId = firebaseUser.uid
                               firebaseFirestore
                                   .collection(Constants.USERS)
                                   .document(firebaseUser.uid)
                                   .set(user)
                           }
                           true
                       }else {
                           Log.d("check", "createUserWithEmailAndPassword:: failure", task.exception)
                           false
                       }
                   }.await()
               if (isSuccess){
                   emit(NetworkResult.Success(true))
               }else{
                   emit(NetworkResult.Error("Oh, something went wrong"))
               }
           }catch (e: Exception){
               emit(NetworkResult.Error(message = e.localizedMessage ?: "Oh, something went wrong!"))
           }
       }
    }

    override suspend fun firebaseLoginIn(
        email: String,
        password: String,
    ): Flow<NetworkResult<Boolean>> {
       return flow {
           var isSuccess = false

           emit(NetworkResult.Loading())

           try {
               firebaseAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener { task ->
                       isSuccess = if (task.isSuccessful){
                           Log.d("check","signInWithEmailAndPassword: success")
                           firebaseAuth.currentUser != null
                       }else {
                           Log.d("check", "signInWithEmailAndPassword: failure", task.exception)
                           false
                       }
                   }.await()
               if (isSuccess){
                   emit(NetworkResult.Success(true))
               }else {
                   emit(NetworkResult.Error("Oh, something went wrong"))
               }
           }catch (e:Exception){
               emit(NetworkResult.Error(message = e.localizedMessage ?: "Oh, something went wrong!"))
           }
       }
    }
}