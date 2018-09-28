package com.mackap.awesomefm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.net.RestClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Mock
  RestClient mRestClient;

  @InjectMocks
  ApiService userService;

  @Test public void emptyTest() {
    RestClient.getApiService().getTopArtist();
  }
}
