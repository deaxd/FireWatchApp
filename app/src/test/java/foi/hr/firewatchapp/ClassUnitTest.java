package foi.hr.firewatchapp;

/**
 * Created by Matija on 30/01/2017.
 */


import junit.framework.Assert;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import foi.hr.members.MembersActivity;

public class ClassUnitTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FcmMessagingService.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Assert.assertNull(MembersActivity.addFragmentsToList());

        System.out.println(result.wasSuccessful());
    }
}