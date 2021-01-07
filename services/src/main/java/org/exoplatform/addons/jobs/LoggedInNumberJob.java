package org.exoplatform.addons.jobs;

import org.exoplatform.addons.listener.LoggedInUserListener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class LoggedInNumberJob implements Job {

    private static final Log LOG = ExoLogger.getLogger(LoggedInNumberJob.class);

    LoggedInUserListener loggedInNumberJob;
    public LoggedInNumberJob() {

        this.loggedInNumberJob = new LoggedInUserListener();
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        LOG.info("The number of logged in users in the last 10 minutes is:"+loggedInNumberJob.countLoggedInUser());


    }
}
