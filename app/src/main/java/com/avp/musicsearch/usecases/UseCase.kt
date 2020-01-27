package com.avp.musicsearch.usecases


/**
 *
 *
 */


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avp.musicsearch.common.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 21 January 2019
 */

abstract class UseCase<in P, R> constructor(
    private val uiScope: CoroutineScope
) {

    private var resultLiveData: MutableLiveData<Either<R>>? = null

    /** Executes the use case asynchronously and places the [Either] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    operator fun invoke(parameters: P, result: MutableLiveData<Either<R>>) {
        Timber.d(this::class.java.canonicalName)

        // Store the result data so that if need loading it can be used later.
        resultLiveData = result

        uiScope.launch {
            try {
                // Check access token is active
                execute(parameters).let { useCaseResult ->
                    result.postValue(
                        Either.Success(
                            useCaseResult
                        )
                    )
                }
            } catch (e: Exception) {
                Timber.e(e)
                result.postValue(Either.Error(e))

            }
        }
    }

    /** Executes the use case asynchronously and returns a [Either] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Either].
     *
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(parameters: P): LiveData<Either<R>> {
        val liveCallback: MutableLiveData<Either<R>> = MutableLiveData()
        //this(parameters, liveCallback)
        uiScope.launch {
            try {
                execute(parameters).let { useCaseResult ->
                    liveCallback.postValue(
                        Either.Success(
                            useCaseResult
                        )
                    )
                }
            } catch (e: Exception) {
                Timber.e(e)
                liveCallback.postValue(Either.Error(e))

            }
        }
        return liveCallback
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R

}