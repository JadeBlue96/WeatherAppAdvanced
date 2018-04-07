package com.example.user.weatherappadvanced.WeatherAppTest.Tests;


import android.content.SharedPreferences;
import android.support.test.filters.SmallTest;

import com.example.user.weatherappadvanced.Activity.SharedCityPreference;
import com.example.user.weatherappadvanced.Activity.SharedPreferenceHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class SharedPreferencesTest {

    private SharedCityPreference sharedCityPreference;


    private SharedPreferenceHelper mockSharedPreferenceHelper;

    private SharedPreferenceHelper mockBrokenSharedPreferenceHelper;

    @Mock
    private
    SharedPreferences mockSharedPreferences;

    @Mock
    private
    SharedPreferences mockBrokenSharedPreferences;

    @Mock
    private
    SharedPreferences.Editor mockEditor;

    @Mock
    private
    SharedPreferences.Editor mockBrokenEditor;

    @Before
    public void initMock() {
        String test_city_name = "London";
        Boolean test_metric = true;
        sharedCityPreference=new SharedCityPreference(test_city_name, test_metric);
        mockSharedPreferenceHelper=createMockSharedPreference();
        mockBrokenSharedPreferenceHelper=createBrokenMockSharedPreference();
    }

    @Test
    public void sharedPreferenceHelper_Save_Read_Information() {
        Boolean success=mockSharedPreferenceHelper.saveCityInfo(sharedCityPreference);
        assertThat("Checking if SharedPreferenceHelper.saveCityInfo returns true.",
                success, is(true));

        SharedCityPreference savedSharedCityPreference =
                mockSharedPreferenceHelper.getCityInfo();
        assertThat("Checking that SharedCityPreference:Name has been read correctly.",
                sharedCityPreference.getName(),
                is(equalTo(savedSharedCityPreference.getName())));
        assertThat("Checking that SharedCityPreference:Metric has been read correctly.",
                sharedCityPreference.getMetric(),
                is(equalTo(savedSharedCityPreference.getMetric())));
    }
    @Test
    public void sharedPreferenceHelper_Save_Read_Information_Failed(){
        Boolean success=mockBrokenSharedPreferenceHelper.saveCityInfo(sharedCityPreference);
        assertThat("Checking that broken SharedPreferenceHelper.saveCityInfo return false.",
                success,is(false));
    }
    private SharedPreferenceHelper createMockSharedPreference() {
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.getKeyCityName()), anyString()))
                .thenReturn(sharedCityPreference.getName());
        when(mockSharedPreferences.getBoolean(eq(SharedPreferenceHelper.getKeyMetric()), anyBoolean()))
                .thenReturn(sharedCityPreference.getMetric());
        when(mockEditor.commit()).thenReturn(true);

        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        return new SharedPreferenceHelper(mockSharedPreferences);
    }
    private SharedPreferenceHelper createBrokenMockSharedPreference() {
        when(mockBrokenEditor.commit()).thenReturn(false);

        when(mockBrokenSharedPreferences.edit()).thenReturn(mockBrokenEditor);
        return new SharedPreferenceHelper(mockBrokenSharedPreferences);
    }


}
