package uz.itschool.lessonfirebase.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import uz.itschool.lessonfirebase.R
import java.util.concurrent.CancellationException

class GoogleAuthUiCLient(
    private val context : Context,
    private val client:SignInClient
){
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender?{
        val result= try {
            client.beginSignIn(
                buildSignInRequest()
            ).await()
        }
        catch (e:Exception){
           e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun getSignInResultFromIntent(intent: Intent):SignInResult{
        val credential = client.getSignInCredentialFromIntent(intent)
        val googleIdToken=credential.googleIdToken
        val googleCredentials =GoogleAuthProvider.getCredential(googleIdToken,null)
        return try {
       val user = auth.signInWithCredential(googleCredentials).await().user
            
        }
        catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
          SignInResult(
              data=null,
              errorMessage = e.message
          )
        }
    }


 private fun buildSignInRequest(): BeginSignInRequest {
     return BeginSignInRequest.Builder()
         .setGoogleIdTokenRequestOptions(
             GoogleIdTokenRequestOptions.builder()
                 .setSupported(true)
                 .setFilterByAuthorizedAccounts(false)
                 .setServerClientId(context.getString(R.string.web_client_id))
                 .build()
         )
         .setAutoSelectEnabled(true)
         .build()
 }
}