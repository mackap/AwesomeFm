package com.mackap.awesomefm.net;

import android.support.annotation.Keep;
import com.google.gson.JsonObject;
import com.mackap.awesomefm.BuildConfig;
import com.mackap.awesomefm.pojo.ArtistInfoResponse;
import com.mackap.awesomefm.pojo.TopArtistsResponse;
import com.mackap.awesomefm.pojo.TopTracksResponse;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


@Keep
public interface ApiService {

  String apiKey = BuildConfig.ApiKey;

  @GET("/2.0/?method=chart.gettoptracks&api_key=" + apiKey + "&format=json")
  Single<TopTracksResponse> getTopTracks();

  @GET("/2.0/?method=chart.gettopartists&api_key=" + apiKey + "&format=json")
  Single<TopArtistsResponse> getTopArtist();

  @GET("/2.0/?method=artist.getinfo&api_key=" + apiKey + "&format=json")
  Single<ArtistInfoResponse> getArtistInfo(@Query("mbid") String mbid);

  @GET("/2.0/?method=artist.gettoptracks&api_key=" + apiKey + "&format=json")
  Call<JsonObject> getArtistTopTracks(@Query("mbid") String mbid);

  @GET("/2.0/?method=artist.gettopalbums&api_key=" + apiKey + "&format=json")
  Call<JsonObject> getArtistTopAlbums(@Query("mbid") String mbid);
}
