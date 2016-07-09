package com.brutcode.seedapp.di.module;

import com.brutcode.seedapp.model.Content;
import com.brutcode.seedapp.di.scope.UserScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Danilo on 04/07/2016.
 */
@Module
public class OmdbApiModule {

    public interface OmdbApiInterface {
//        http://www.omdbapi.com/?t=terminator+2&y=&type=movie&plot=short&r=json
//        http://www.omdbapi.com/?type=movie&plot=short&r=json&t=terminator+2&y=&
        @GET("/?type=movie&plot=full&r=json")
        Observable<Response<Content>> getContent(@Query(value="t",encoded = true)String title, @Query("y")String year);
    }

    @Provides
    @UserScope // needs to be consistent with the component scope
    public OmdbApiInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(OmdbApiInterface.class);
    }
}
