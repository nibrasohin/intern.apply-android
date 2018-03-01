package intern.apply.internapply;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.RatingBar;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.List;

import intern.apply.internapply.api.InternAPI;
import intern.apply.internapply.model.Job;
import intern.apply.internapply.model.JobRating;
import intern.apply.internapply.view.mainactivity.MainActivity;
import intern.apply.internapply.view.viewjobactivity.ViewJobActivity;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RateJobAcceptanceTest extends ActivityInstrumentationTestCase2<ViewJobActivity> {
    private static final String ACTIVITY_ERROR = "wrong activity";
    private static final String RATING_ERROR = "error with stars";
    private static final String VOTES_ERROR = "error with votes number";
    private Solo solo;
    private final InternAPI api;
    private JobRating jobRating;
    RatingBar ratingBar;

    public RateJobAcceptanceTest(){
        super(ViewJobActivity.class);
        api = mock(InternAPI.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityIntent(new Intent().putExtra("TEST", true));
        solo = new Solo(getInstrumentation(), getActivity());
        jobRating = new JobRating(1.00, 1);

        Job fakeJob = new Job(
                "fake org",
                "fake title",
                "fake location",
                "fake description");
        List<Job> fakeJobList = new ArrayList<Job>();
        fakeJobList.add(fakeJob);
        Observable<List<Job>> output = Observable.just(fakeJobList);
        when(api.getJob(anyInt())).thenReturn(output);
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }


    public void testRateJob() {
        solo.assertCurrentActivity(ACTIVITY_ERROR, ViewJobActivity.class);
        solo.waitForActivity(ViewJobActivity.class);

        ArrayList<JobRating> fakeRating = new ArrayList<>();
        fakeRating.add(jobRating);
        Observable<List<JobRating>> output = Observable.just(fakeRating);
        when(api.getJobRating(anyInt())).thenReturn(output);
        getActivity().setApi(api);

        solo.clickOnView(solo.getView(R.id.ratingBar));

        ratingBar = (RatingBar)solo.getView(R.id.ratingBar);
        assertTrue(RATING_ERROR,ratingBar.getRating() == jobRating.getScore());
        assertTrue(VOTES_ERROR, solo.searchText(jobRating + " votes"));
    }



}