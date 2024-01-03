package com.arshapshap.versati.feature.auth.data.repository

import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

internal class AuthRepositoryImpl : AuthRepository {

    override suspend fun logIn(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
    }

    override suspend fun register(email: String, username: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }

    override suspend fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}